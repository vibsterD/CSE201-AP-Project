package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.Random;

public class Island extends GameObject implements Serializable {

    final static Random r1 = new Random(786);

    public Island(Position pos) {
        this.position = pos;
        int island_get = 1+r1.nextInt(11);
//        System.out.println("CREATING ISLAND");
        url = "com/ap43iiitd/willhero/imageres/Sprites/Islands/ (".concat(String.valueOf(island_get)).concat(").png");
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
    }



    public void addToScene(AnchorPane pane, int x, int y) {
        image_fx.setPreserveRatio(true);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        imageWidth = 200;
        image_fx.setFitWidth(imageWidth);
        pane.getChildren().add(image_fx);
    }

    @Override
    public void collide(GameObject o1) {
    }
}
