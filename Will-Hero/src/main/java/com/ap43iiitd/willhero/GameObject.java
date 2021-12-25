package com.ap43iiitd.willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private double gravity;
    protected ImageView image_fx;
    private int restitution;
    protected Position position;

    public abstract void collide(GameObject o1);
    public ImageView getImage_fx() {
        return image_fx;
    }

    public Position getPosition() {
        return position;
    }
}



