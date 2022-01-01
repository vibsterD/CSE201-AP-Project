package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private double gravity;
    transient protected ImageView image_fx;
    private int restitution;
    protected String url;
    private static final int serialVersionUID = 1;
    protected Position position;
    protected double x;
    protected double y;
    protected double imageWidth = 100;

    public abstract void collide(GameObject o1);
    public ImageView getImage_fx() {
        return image_fx;
    }

    public Position getPosition() {
        return position;
    }

    public void reload(AnchorPane pane){
        this.image_fx = new ImageView(new Image(url));
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(imageWidth);
        image_fx.setTranslateX(x);
        image_fx.setTranslateY(y);
        pane.getChildren().add(image_fx);
    }
}



