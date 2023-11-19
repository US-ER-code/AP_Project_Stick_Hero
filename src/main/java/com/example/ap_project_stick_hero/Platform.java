package com.example.ap_project_stick_hero;

import javafx.scene.shape.Rectangle;

public class Platform {
    double center;
    double width;
    public Platform(Rectangle center, Rectangle platform){
        this.center = center.getLayoutX();
        this.width = platform.getWidth();
    }
}
