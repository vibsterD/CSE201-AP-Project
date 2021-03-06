package com.ap43iiitd.willhero;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.Serializable;

public class Position implements Serializable {
    private double vel_x;
    private double vel_y;
    private double gravity = 10;
    private double x_drag = 0;
    private Boolean timeout = false;
    transient TranslateTransition smoothenTransition;

    public Position() {
        this.vel_x = 0;
        this.vel_y = 0;
    }

    public Position(int gravity){
        this();
        this.gravity = gravity;
    }

    public Position(Boolean timeout){
        this.timeout = timeout;
    }

    public double getVel_x() {
        return vel_x;
    }

    public double getVel_y() {
        return vel_y;
    }

    public void updatePosition(ImageView imageView) {
        if(timeout && this.vel_x<=0) imageView.setOpacity(0); //disappearing throwables
        if(smoothenTransition==null) {
            smoothenTransition = new TranslateTransition(Duration.millis(1000.0 / 60.0));
        }

        this.vel_y = vel_y + gravity/250;
        smoothenTransition.setNode(imageView);
        smoothenTransition.setInterpolator(Interpolator.LINEAR);
        smoothenTransition.setByY(vel_y);

        if(this.vel_x > 0) {
            x_drag = 0.3;
        }
        else {
            x_drag = 0;
            vel_x = 0;
        }

        this.vel_x = this.vel_x - x_drag;
        smoothenTransition.setByX(vel_x);
        smoothenTransition.play();
    }

    public void setVelocity(double vel_x, double vel_y) {
        this.vel_x = vel_x;
        this.vel_y = vel_y;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

}
