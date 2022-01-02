package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PowerUpBrick extends GameObject{
    private final PowerUpMushroom mushroom;
    private Boolean exhausted;

    public PowerUpBrick(PowerUpMushroom mushroom) {
        this.mushroom = mushroom;
        this.exhausted = false;
        position = new Position(0);
        url = "com/ap43iiitd/willhero/imageres/Sprites/bonus_brick.png";
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
    }

    @Override
    public void collide(GameObject o1) {

    }

    public void reward() {
        if(!exhausted) {
            exhausted = true;
            mushroom.setUnlocked(true);
            mushroom.getPosition().setGravity(10);
            mushroom.getPosition().setVelocity(50, -15);
        }
    }

    public void addToScene(AnchorPane pane, int x, int y) {
        image_fx.setPreserveRatio(true);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        imageWidth = 100;
        image_fx.setFitWidth(imageWidth);
        view_order = 20000.0;
        image_fx.setViewOrder(view_order);
        pane.getChildren().add(image_fx);
    }


}
