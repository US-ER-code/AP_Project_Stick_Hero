package com.example.ap_project_stick_hero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayController.fxml"));
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void endGame(){
        System.exit(0);
    }

    public Parent getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }
}