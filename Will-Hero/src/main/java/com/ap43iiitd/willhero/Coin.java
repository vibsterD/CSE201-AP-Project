package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class Coin extends GameObject{
    public Boolean collided;
    final static Random r1 = new Random(1337);

    public Coin() {
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/coin.png");
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
        image_fx.setFitWidth(30);
        image_fx.setViewOrder(1000);

        double delta;
        double delta_min = 100000;
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
//        chest.setLayoutX(150);
//        chest.setLayoutY(400);
        game_screen.getChildren().add(image_fx);

        double y_delta = island.getImage_fx().getBoundsInParent().getMinY() - image_fx.getBoundsInParent().getMaxY();
//        y_delta = 0;
        image_fx.setLayoutY(image_fx.getLayoutY() + y_delta - 10);
        System.out.println("Coin!");
    }

}
