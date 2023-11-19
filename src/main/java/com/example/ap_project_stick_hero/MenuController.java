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
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GameplayController.fxml")));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        Button exit = new Button();
        exit.setOnAction(actionEvent ->{
            endgame();
        });
        root.getChildrenUnmodifiable().add(exit);
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    public void showhighscore(ActionEvent e) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Highscore.fxml")));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void endgame(){
        System.exit(0);
    }
}
