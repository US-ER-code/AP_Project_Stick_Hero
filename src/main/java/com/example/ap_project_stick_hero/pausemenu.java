package com.example.ap_project_stick_hero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class pausemenu {
    @FXML
    private AnchorPane pauseAnchorPane;

    @FXML
    public void saveGame() throws IOException, ClassNotFoundException {
        GameplayController gpc = new GameplayController();
        gpc.saveGame();
    }
}
