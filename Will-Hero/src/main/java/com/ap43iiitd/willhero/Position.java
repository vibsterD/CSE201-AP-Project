package com.ap43iiitd.willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Position implements Serializable {
    private int vel_x;
    private int vel_y;

    public Position() {
        this.vel_x = 0;
        this.vel_y = 0;
    }

    public int getVel_x() {
        return vel_x;
    }

    public int getVel_y() {
        return vel_y;
    }

    public void updatePosition(ImageView imageView) {
        System.out.println(imageView);
    }

    public void setVelocity(int vel_x, int vel_y) {
        this.vel_x = vel_x;
        this.vel_y = vel_y;
    }
}
