package com.example.ap_project_stick_hero;

public class Game {
    private Player player;
    private String name;//name in order to identify and load an existing game
    public Game(GameplayController c){
        //this.player = new Player();
    }
    public void saveGame(LoadGameController controller){
        this.name = name;
        controller.saveGame(this);
    }

}
