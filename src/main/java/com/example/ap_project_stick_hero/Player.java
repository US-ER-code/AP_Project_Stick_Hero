package com.example.ap_project_stick_hero;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;

public class Player {
    private int currentScore;
    private int highScore;
    private int numBerries;
    private int inGame;//1 if in a game 0 if not in game
    private int flipped;//1 if character flipped on a stick, 0 if not flipped
    private Node character;
    public Node getCharacter() {
        return character;
    }
    private PlayerAnimation playerAnimation;
    public Player(Node node){
        this.currentScore = 0;
        this.highScore = 0;
        this.numBerries = 0;
        this.inGame = 1;
        this.flipped = 0;
        this.character = node;
        this.playerAnimation = new PlayerAnimation(node);
    }

    public int getInGame() {
        return inGame;
    }

    public void setInGame(int inGame) {
        this.inGame = inGame;
    }

    public int getNumBerries() {
        return numBerries;
    }

    public void setNumBerries(int numBerries) {
        this.numBerries = numBerries;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getFlipped() {
        return flipped;
    }

    public void setFlipped(int flipped) {
        this.flipped = flipped;
    }
    public void walkStick(Stick stick, Duration duration, Animation onFinish){
        playerAnimation.walkOnStick(stick.getLength(),duration,onFinish);
    }
    public void stopWalking() {
        playerAnimation.stopWalking();
    }
    public void fall(Duration duration, Runnable onFinishedAction){
        //the character falls if the player is unable to build the stick of correct length
        TranslateTransition fallAnimation = new TranslateTransition(duration, this.character);
        fallAnimation.setToY(500);
        fallAnimation.play();
        if (onFinishedAction != null) {
            fallAnimation.setOnFinished(event -> onFinishedAction.run());
        }
        fallAnimation.play();
    }
    public void collectBerry(){
        this.numBerries++;
    }
    public void flip() {
        if(this.flipped == 0){
            this.flipped = 1;
        }else this.flipped = 0;
    }
}
