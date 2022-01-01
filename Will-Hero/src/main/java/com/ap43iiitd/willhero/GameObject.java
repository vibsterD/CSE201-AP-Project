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
    private static final int serialVersionUID = 69;
    protected Position position;
    protected double x;
    protected double y;
    protected double imageWidth = 100;
    protected Double view_order = null;

    public abstract void collide(GameObject o1);
    public ImageView getImage_fx() {
        return image_fx;
    }

    public Position getPosition() {
        return position;
    }

    public void setXY(){
        this.x = image_fx.getTranslateX()+image_fx.getLayoutX();
        this.y = image_fx.getTranslateY()+image_fx.getLayoutY();
    }

    public void reload(AnchorPane pane){
        this.image_fx = new ImageView(new Image(url));
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(imageWidth);
        image_fx.setTranslateX(x);
        image_fx.setTranslateY(y);
        if(view_order != null) {
            image_fx.setViewOrder(view_order);
        }
        pane.getChildren().add(image_fx);
    }
}



