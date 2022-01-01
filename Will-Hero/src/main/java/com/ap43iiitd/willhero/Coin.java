package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Coin extends GameObject{
    public Boolean collided;
    final static Random r1 = new Random(1337);

    public Coin() {
        url = "com/ap43iiitd/willhero/imageres/Sprites/coin.png";
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
        this.position = new Position(0);
        this.collided = false;
    }

    @Override
    public void collide(GameObject o1) {
        if(collided == false) {
            if (o1 instanceof Player) {
                Player player = (Player) o1;
                if(player.getImage_fx().getBoundsInParent().intersects(image_fx.getBoundsInParent())) {
                    player.updateCoins(5);
                    collided = true;
                    image_fx.setVisible(false);
                    image_fx.setDisable(true);
                }
            }
        }
    }

    public void addToScene(AnchorPane game_screen, int x, int y, Island island) {
        image_fx.setPreserveRatio(true);
        imageWidth = 30;
        image_fx.setFitWidth(imageWidth);
        view_order = 1000.0;
        image_fx.setViewOrder(view_order);

        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);

        game_screen.getChildren().add(image_fx);

        double y_delta = island.getImage_fx().getBoundsInParent().getMinY() - image_fx.getBoundsInParent().getMaxY();

        image_fx.setLayoutY(image_fx.getLayoutY() + y_delta - 10);
    }

}
