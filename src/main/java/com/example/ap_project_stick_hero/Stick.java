package com.example.ap_project_stick_hero;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Stick {
    private double length;
    private Rectangle rectangle;

    public Stick(Rectangle rectangle){
        this.rectangle = rectangle;
        double s1 = rectangle.getHeight(),s2 = rectangle.getWidth();
        if(s1>s2){
            this.length = s1;
        }else this.length = s2;
    }
    public void fall(Duration duration){
        //the stick falls when the player builds a stick of incorrect length
        TranslateTransition fallAnimation = new TranslateTransition(duration, this.rectangle);
        fallAnimation.setToY(500); // adjust the Y-coordinate accordingly
        fallAnimation.play();
    }

    public double getLength() {
        return length;
    }
}
