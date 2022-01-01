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
    protected double scaleX=1;
    protected double scaleY=1;

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
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        image_fx.setScaleX(scaleX);
        image_fx.setScaleY(scaleY);
        pane.getChildren().add(image_fx);
    }
}



