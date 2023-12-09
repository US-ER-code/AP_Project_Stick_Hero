package com.example.ap_project_stick_hero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameOverController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private Button goHomeButton;
    @FXML
    private Button restartButton;
    public void goHome(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuController.fxml")));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayController.fxml"));
        Parent root = loader.load();
//        GameplayController gameplayController = loader.getController();
//        gameplayController.initialize(event);

        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }
}
