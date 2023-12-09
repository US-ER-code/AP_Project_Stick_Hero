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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class GameOverController {
    private Player player;
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private Label reviveMessage;
    @FXML
    private Button reviveButton;
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
    @FXML
    private Label berryCount;
    public void goHome(ActionEvent event) throws IOException {
        this.root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MenuController.fxml")));
        this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    public void initialize() throws FileNotFoundException {
        this.currentScoreLabel.setText(String.valueOf(this.currentScore));
        this.highScoreLabel.setText(String.valueOf(this.highScore));
        Scanner countBerriesScanner = null;
        Scanner highScoreScanner = null;
        int n,high;
        try {
            countBerriesScanner = new Scanner(new File("BerryCount.txt"));
            highScoreScanner = new Scanner(new File("Highscore.txt"));
            n = countBerriesScanner.nextInt();
            high = highScoreScanner.nextInt();
        }finally {
            if(countBerriesScanner!= null){
                countBerriesScanner.close();
            }
            if(highScoreScanner != null){
                highScoreScanner.close();
            }
        }
        this.berryCount.setText(String.valueOf(n));
        this.highScoreLabel.setText(String.valueOf(high));

    }
    @FXML
    public void startGame(ActionEvent event) throws IOException {
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayController.fxml"));
        Parent root = loader.load();
        this.scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
        this.currentScoreLabel.setText(String.valueOf(this.currentScore));
    }

    public int getHighScore() {
        return highScore;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    @FXML
    public void revivePlayer(ActionEvent event) throws IOException {
        Scanner countBerriesScanner = null;
        int n;
        try {
            countBerriesScanner = new Scanner(new File("BerryCount.txt"));
            n = countBerriesScanner.nextInt();
        }finally {
            if(countBerriesScanner!= null){
                countBerriesScanner.close();
            }
        }
        if(n<5){
            reviveMessage.setText("(Not enough berries. Need 5 or more to revive.)");
        }else {
            this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("GameplayController.fxml"));
            Parent root = loader.load();
            GameplayController gameplayController = loader.getController();
            gameplayController.setScoreBoardText(this.player.getCurrentScore());
            n-=5;
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter("BerryCount.txt");
                printWriter.println(n);
            }finally {
                if(printWriter != null){
                    printWriter.close();
                }
            }
            this.scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.show();
        }
    }
}
