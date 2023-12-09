package com.example.ap_project_stick_hero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class GameOverController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private Button goHomeButton;
    @FXML
    private Button restartButton;
    @FXML
    private Label currentScoreLabel;
    private int highScore;
    private int currentScore;
    @FXML
    private Label highScoreLabel;
//    public GameOverController(Player player){
//        if(player.getCurrentScore()>this.highScore){
//            this.highScore = player.getHighScore();
//        }
//        this.currentScore = player.getCurrentScore();
//    }
    public void goHome(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuController.fxml")));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    public void initialize(){
//        Scanner in = null;
//        Scanner in1 = null;
//        PrintWriter printWriter = null;
//        int n;
//        int high;
//        try{
//            in = new Scanner("CurrentScore.txt");
//            n = in.nextInt();
//            in1 = new Scanner("Highscore.txt");
//            high = in1.nextInt();
//            if(high < n){
//                high = n;
//                printWriter = new PrintWriter("Highscore.txt");
//                printWriter.println(high);
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }finally {
//            if (in != null) {
//                in.close();
//            }
//            if (in1 != null) {
//                in1.close();
//            }
//            if (printWriter != null) {
//                printWriter.close();
//            }
//        }
//        this.highScore = high;
//        this.currentScore = n;
        this.currentScoreLabel.setText(String.valueOf(this.currentScore));
        this.highScoreLabel.setText(String.valueOf(this.highScore));
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

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getHighScore() {
        return highScore;
    }
}
