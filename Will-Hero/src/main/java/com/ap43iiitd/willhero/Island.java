package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class Island extends GameObject implements Serializable {
    @FXML
//    private ImageView image_fx;

    private int restitution;




    public Island(int restitution, Position pos) {
        this.position = pos;
        this.restitution = restitution;
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Islands/T_Islands_01.png");
        this.image_fx = new ImageView(image);
    }



    public void addToScene(AnchorPane pane, int x, int y) {
//        island_fx.setScaleX(0.2);
//        island_fx.setScaleY(0.2);
        image_fx.setPreserveRatio(true);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        image_fx.setFitWidth(200);
//        island_fx.setFitHeight(150);
        pane.getChildren().add(image_fx);
    }

    @Override
    public void collide(GameObject o1) {
        return;
    }
}
