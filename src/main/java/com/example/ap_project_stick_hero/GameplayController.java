package com.example.ap_project_stick_hero;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameplayController {
    @FXML
    Player player;
    Rectangle startCenter;
    Rectangle endCenter;
    Rectangle startPlatform;
    Rectangle endPlatform;
    Platform wholeStartPlatform;
    Platform wholeEndPlatform;
    public GameplayController(){
        this.player = new Player();
        this.startCenter.setWidth(19);
        this.startCenter.setHeight(10);
        this.startCenter.setLayoutX(56);
        this.startCenter.setLayoutY(333);
        this.startCenter.setFill(Color.RED);

    }
}
