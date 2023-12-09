package com.example.ap_project_stick_hero;
import javafx.animation.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameplayController {
    double MAX_STICK_HEIGHT = 1000;
    private double gap;
    private String gameOverMessage;
    @FXML
    private Rectangle stick;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Player player;
    @FXML
    private Label scoreBoard;
    @FXML
    private Rectangle startPlatform;
    @FXML
    private Rectangle endPlatform;
    @FXML
    private Node character;
    private Game game;
    private Rectangle nextPlatform;
    private int built = 0;
    private boolean isBuildingStick = false;  // Flag to track stick building
    private boolean stickRotated = false;
    private double stickBuildDuration = 0.0;   // Duration for stick building in seconds
    private static final double MAX_STICK_LENGTH = 100.0;  // Adjust as needed
    private SequentialTransition sequentialTransition;
    private void resetFlags(){
        this.built = 0;
        this.isBuildingStick = false;
        this.stickRotated = false;
        this.stickBuildDuration = 0.0;
    }
    public GameplayController(){
    }
    public double getStickBuildDuration() {
        return stickBuildDuration;
    }

    public void setStickBuildDuration(double stickBuildDuration) {
        this.stickBuildDuration = stickBuildDuration;
    }

    public boolean isBuildingStick() {
        return isBuildingStick;
    }

    public void setBuildingStick(boolean buildingStick) {
        isBuildingStick = buildingStick;
    }
    @FXML
    private void stopBuildingStick() throws InterruptedException {
        System.out.println("Mouse released");
        isBuildingStick = false;
        this.startStickRotation(()-> {
            // Use Platform.runLater() for UI updates
            boolean isValidStick = checkValidStick();
            this.walkStick(() -> {
                if (isValidStick) {
                    this.proceedToNextStage();
                } else {
                    Stick stick1 = new Stick(this.stick);
                    stick1.fall(Duration.seconds(1));
                    this.player.fall(Duration.seconds(1),()->{
                        this.displayGameOver();
                    });
                }
            });
        });
//        this.walkStick();
//        this.proceedToNextStage();
    }
    @FXML
    private void startBuildingStick(MouseEvent event) {
        if(this.built == 1){
            return;
        }
        System.out.println("Mouse pressed");
        this.isBuildingStick = true;

        // Store the initial y-coordinate
        double initialY = this.stick.getY();
        this.stickBuildDuration = 0.0;
        Duration cycleDuration  = Duration.millis(100);
        // Set up a timeline to update stick height
        Timeline timeline = new Timeline(new KeyFrame(cycleDuration, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateStickHeight(initialY);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        this.built = 1;

    }
    @FXML
    private void updateStickHeight(double initialY) {
        if (this.isBuildingStick) {
            double newY = initialY - (stickBuildDuration * 100);


            // Adjust the stick height and y-coordinate
            double newHeight = stickBuildDuration * 100;
            this.stick.setHeight(newHeight);
            this.stick.setY(newY);

            // Increment the build duration
            this.stickBuildDuration += 0.1;
            // Check if the maximum height is reached

        }
    }
    @FXML
    public void stickBuild(MouseEvent event){
        this.startBuildingStick(event);

    }
    private void createRandomPlatform() {
        // Set the initial position of the platform
        double initialPlatformX = this.startPlatform.getX();
        double initialPlatformY = this.startPlatform.getY();

        // Set a maximum and minimum distance for the new platform
        double minDistance = 50.0;
        double maxDistance = 345.0;

        // Generate a random distance for the new platform
        // Generate a random distance for the new platform
        Random random = new Random();
        double distance = minDistance + (random.nextDouble() * (maxDistance - minDistance));
        double height = 165;
        double width = 20 + (random.nextDouble() * (150 - 20));

        // Calculate the new platform's position
        double newPlatformX = distance;
        double newPlatformY = initialPlatformY;
        this.gap = newPlatformX-width/2-this.startPlatform.getWidth();

        // Update the platform position
        this.endPlatform.setHeight(height);
        this.endPlatform.setWidth(width);
        this.endPlatform.setX(newPlatformX);
        this.endPlatform.setY(newPlatformY);
        this.endPlatform.setFill(Color.BLACK);
    }
    @FXML
    public void initialize(){
        this.player = new Player(this.character);
        this.createRandomPlatform();
    }
    private void startStickRotation(Runnable callback) {
        // Set up a timeline to rotate the stick
        this.sequentialTransition = new SequentialTransition();
        if(this.stickRotated){
            return;
        }
        Timeline rotationTimeline = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rotateStick();
            }
        }));
        rotationTimeline.setCycleCount(10); // Adjust the cycle count as needed
        rotationTimeline.setOnFinished(event -> callback.run());

        // Add the rotation timeline to the sequential transition
        this.sequentialTransition.getChildren().add(rotationTimeline);

        // Play the sequential transition
        this.sequentialTransition.play();
    }
    private void rotateStick() {
        // Rotate the stick by 9 degrees per frame

        Rotate rotation = new Rotate(9);

        // Set the pivot point at the bottom center of the stick
        rotation.setPivotX(this.stick.getX() + this.stick.getWidth() / 2);
        rotation.setPivotY(this.stick.getY() + this.stick.getHeight());

        // Apply the rotation transformation to the stick
        this.stick.getTransforms().add(rotation);

        // Check if the desired rotation (90 degrees) is reached
        if (this.stick.getRotate() >= 90) {
            // Stop the rotation animation
            stopStickRotation();
        }
        this.stickRotated = true;
    }

    private void stopStickRotation() {
        // Optionally, you can perform any cleanup or additional actions here
        System.out.println("Stick rotation completed!");
        this.stickRotated = true;
    }

    private void walkStick(Runnable callback){
        Stick stick1 = new Stick(this.stick);
        this.sequentialTransition = new SequentialTransition();
        Duration walkDuration = Duration.seconds(2);
        player.walkStick(stick1, Duration.seconds(2),sequentialTransition);
        sequentialTransition.setOnFinished(event -> {
            if (callback != null) {
                callback.run();
            }
        });
    }

    private boolean checkValidStick(){
        double s1 = this.stick.getHeight(),s2 = this.stick.getWidth();
        double stickLength;
        if(s1>s2){
            stickLength = s1;
        }else {
            stickLength = s2;
        }
        double stickEndX = this.stick.getX() + this.stick.getWidth();

        if (this.endPlatform.getX() < stickLength && this.endPlatform.getX()+this.endPlatform.getWidth() >= stickLength) {
            // Stick's end is within the bounds of the platform
            System.out.println("Valid");
            return true;
        } else {
            // Stick's end is outside the bounds of the platform
            System.out.println("Invalid");
            return false;
        }
    }
    private void stickToDefault(){
        this.stick.setWidth(5);
        this.stick.setHeight(0);
        this.stick.setX(0);
        this.stick.setY(0);
        this.stick.setRotationAxis(new Point3D(0,0,1));
        this.stick.getTransforms().clear();
        this.stickRotated = false;

    }
    private void proceedToNextStage(){
        Stick stick1 = new Stick(this.stick);
        if (this.checkValidStick()) {
            this.player.setCurrentScore(this.player.getCurrentScore() + 1);

            // Use Platform.runLater() for UI updates
            Platform.runLater(() -> {
                this.scoreBoard.setText(String.valueOf(this.player.getCurrentScore()));

                // Wait for the sequential transition to complete before resetting the character
                this.sequentialTransition.setOnFinished(event -> {
                    this.stickBuildDuration = 0.0;
                    this.character.setLayoutX(this.character.getLayoutX()-this.stick.getHeight()); // or the appropriate initial value
                    this.resetFlags();
                    this.stickToDefault();
                    this.createRandomPlatform();

                });

                // Start the next animation
                this.sequentialTransition.play();
            });
        }else {
            Platform.runLater(this::displayGameOver);
        }

    }
    private class Proceed extends Thread{
        private GameplayController gameplayController;
        Proceed(GameplayController gameplayController){
            this.gameplayController = gameplayController;
        }

        @Override
        public void run() {
            gameplayController.proceedToNextStage();
        }
    }
    private class WalkStick extends Thread{
        private GameplayController gameplayController;
        WalkStick(GameplayController g){
            this.gameplayController = g;
        }

        @Override
        public void run() {
            //this.gameplayController.walkStick();
        }
    }
    private void displayGameOver() {
        try {
            this.stage = (Stage) this.stick.getScene().getWindow(); // Assuming stick is part of the scene

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverController.fxml"));
            Parent root = loader.load();

            // Access the controller and set the message if needed
            GameOverController gameOverController = loader.getController();

            this.scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void display_pause_menu() throws IOException {
        try {
            this.stage = (Stage) this.stick.getScene().getWindow(); // Assuming stick is part of the scene

            FXMLLoader loader = new FXMLLoader(getClass().getResource("pausemenu.fxml"));
            Parent root = loader.load();

            // Access the controller and set the message if needed
            pausemenu gamePauseController = loader.getController();

            this.scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() throws IOException,ClassNotFoundException {
        Game g = new Game();
        g.saveGame(character);
    }
}
