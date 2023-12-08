package com.example.ap_project_stick_hero;

import javafx.scene.Node;
import javafx.scene.Parent;

public class Player {
    private int currentScore;
    private int highScore;
    private int numBerries;
    private int inGame;//1 if in a game 0 if not in game
    private int flipped;//1 if character flipped on a stick, 0 if not flipped
    private Stick currentStick;
    private Node character;
    public Player(Node node){
        this.currentScore = 0;
        this.highScore = 0;
        this.numBerries = 0;
        this.inGame = 1;
        this.flipped = 0;
        this.character = node;
    }
    public void revive(){
        this.numBerries -= 10;
        this.inGame = 1;
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
    public void flipCharacter(){
        this.flipped = 1;
    }
    public void unFlipCharacter(){
        this.flipped = 0;
    }

    public void walkStick(){

    }

    public void flip(){
        //flip while walking on stick
        //will only be called if the character is walking on the stick
    }

    public void fall(){
        //the character falls if the player is unable to build the stick of correct length
    }

    public void collectBerry(){
        this.numBerries++;
    }
    public void buildStick(double length){
        //this.currentStick = new Stick(length);
    }
}
