package com.example.ap_project_stick_hero;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class PlayerAnimation {

    private TranslateTransition walkTransition;
    private Animation onFinishCallback;  // New field for the onFinish callback
    private Node character;
    private boolean isWalking;
    public PlayerAnimation(Node character) {
        this.walkTransition = new TranslateTransition();
        this.walkTransition.setNode(character);
        this.character = character;
    }

    // Updated walkOnStick method to accept an onFinish callback
    public void walkOnStick(double stickLength, Duration duration, Animation onFinish) {
        this.isWalking = true;
        walkTransition.setByX(stickLength);
        walkTransition.setDuration(duration);

        // Set the onFinish callback
        onFinishCallback = onFinish;

        // Set up the onFinished event
        walkTransition.setOnFinished(event -> {
            stopWalking();  // Stop the current animation
            onFinishCallback.play();  // Play the onFinish callback animation
        });

        walkTransition.play();
    }

    public void stopWalking() {
        walkTransition.stop();
        this.isWalking = false;
    }

    public TranslateTransition getWalkTransition() {
        return walkTransition;
    }
    public void flipCharacter() {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.1), this.character);
        rotateTransition.setByAngle(180);  // Adjust the angle as needed
        rotateTransition.play();
    }

    public boolean isWalking() {
        return isWalking;
    }

    public void setWalking(boolean walking) {
        isWalking = walking;
    }
}
