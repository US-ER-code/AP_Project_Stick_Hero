package com.example.ap_project_stick_hero;

import javafx.scene.shape.Rectangle;

public class Stick {
    private double length;
    private Rectangle rectangle;

    public Stick(Rectangle rectangle){
        this.rectangle = rectangle;
        this.length = rectangle.getWidth();
    }
    public void fall(){
        //the stick falls when the player builds a stick of incorrect length
    }
}
