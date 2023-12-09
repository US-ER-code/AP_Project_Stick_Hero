package com.example.ap_project_stick_hero;

import javafx.scene.Node;

import java.io.*;

public class Game implements Serializable{
    private Player player;
    private String name;//name in order to identify and load an existing game
    private int Score;
    private int numBerries;
    public void saveGame(Node node) throws IOException,ClassNotFoundException {
        Player p = new Player(node);
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("Game.txt"));
            LoadGameController lgc = new LoadGameController();
            lgc.Score = p.getCurrentScore();
            lgc.numBerries = p.getNumBerries();
            oos.writeObject(lgc);
        }
        finally{
            if(oos != null){
                oos.close();
            }
        }
    }
    public void loadGame(Node node) throws IOException,ClassNotFoundException{
        ObjectInputStream ois = null;
        Player p = new Player(node);
        try{
            ois = new ObjectInputStream(new FileInputStream("Game.txt"));
            p = (Player)ois.readObject();
        }
        finally{
            if(ois != null){
                ois.close();
            }
        }
    }
}
