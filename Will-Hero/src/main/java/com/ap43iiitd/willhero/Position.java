package com.ap43iiitd.willhero;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class Position implements Serializable {
    private double vel_x;
    private double vel_y;
    private final double gravity = 20;
    private Boolean dash_flag = false;

    private final TranslateTransition splineTranslate = new TranslateTransition(Duration.millis(1000.0/250.0));

    public Position() {
        this.vel_x = 0;
        this.vel_y = 0;
    }

    public double getVel_x() {
        return vel_x;
    }

    public double getVel_y() {
        return vel_y;
    }

    public void updatePosition(ImageView imageView) {

        this.vel_y = vel_y + gravity/1620;
        splineTranslate.setNode(imageView);
        splineTranslate.setInterpolator(Interpolator.LINEAR);
        splineTranslate.setByY(vel_y);
        splineTranslate.play();
//        System.out.println(this.vel_y);
        if(!dash_flag) {
            // update X
        }

        //imageView.setTranslateY(imageView.getTranslateY()+vel_y);
    }

    public void setVelocity(double vel_x, double vel_y) {
        this.vel_x = vel_x;
        this.vel_y = vel_y;
    }


}
