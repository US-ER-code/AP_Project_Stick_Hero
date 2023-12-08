package com.example.ap_project_stick_hero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoadGameController {
    @FXML
    private Game[] games; // array to store saved games
    public LoadGameController(){

    }
    public void saveGame(Game game){
        //add the game passed as parameter to the games array
    }

    @FXML
    public void displayGames(){
        //display games in games array as buttons in order to load an existing game
    }

    @FXML
    public void goToMenu(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MyApplication.class.getResource("MenuController.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();
    }
}
