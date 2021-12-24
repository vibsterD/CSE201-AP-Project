package com.ap43iiitd.willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Position implements Serializable {
    private double vel_x;
    private double vel_y;
    private final double gravity = 10;

    public Position() {
        this.vel_x = 0;
        this.vel_y = -10;
    }

    public double getVel_x() {
        return vel_x;
    }

    public double getVel_y() {
        return vel_y;
    }

    public void updatePosition(ImageView imageView) {

        this.vel_y = vel_y + gravity/60;
        imageView.setTranslateY(imageView.getTranslateY()+vel_y);
    }

    public void setVelocity(int vel_x, int vel_y) {
        this.vel_x = vel_x;
        this.vel_y = vel_y;
    }
}
