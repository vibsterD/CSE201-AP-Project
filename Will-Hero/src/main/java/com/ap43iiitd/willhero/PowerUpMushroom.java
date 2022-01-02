package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PowerUpMushroom extends GameObject{
    private Boolean collided;
    private Boolean unlocked;

    public PowerUpMushroom() {
        collided = false;
        position = new Position(0);
        url = "com/ap43iiitd/willhero/imageres/Sprites/bonus_mushroom.png";
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
        unlocked = false;
    }

    @Override
    public void collide(GameObject o1) {
        if(collided == false && unlocked) {
            if (o1 instanceof Player) {
                Player player = (Player) o1;
                if(player.getImage_fx().getBoundsInParent().intersects(image_fx.getBoundsInParent())) {
                    player.setImageWidth(player.getImageWidth() + 5);
                    player.getImage_fx().setFitWidth(player.getImageWidth());
                    collided = true;
                    visibility = false;
                    image_fx.setVisible(visibility);
                    image_fx.setDisable(true);
                }
            }

            if (o1 instanceof Island) {
                // island
                Island island = (Island) o1;
                if (this.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                    // mushroom
                    // island
                    double height = island.getImage_fx().getFitHeight() / 2;
                    if (this.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
//                    System.out.println("Collided with island");
                        this.getPosition().setVelocity(0, -3);
                    }


                    // mushroom Island

                    double island_min_x = island.getImage_fx().getBoundsInParent().getMinX();
                    double mushroom_max_x = this.getImage_fx().getBoundsInParent().getMaxX();
                    double x_del_right = Math.abs(island_min_x - mushroom_max_x);
//                System.out.println("XDEL: " + x_del_right);
                    if(x_del_right < 50.0) {
//                    System.out.println("COLLIDING ORC");
                        position.setVelocity(0, position.getVel_y());
                    }


                }



            }
        }
    }

    public void addToScene(AnchorPane pane, int x, int y) {
        image_fx.setPreserveRatio(true);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        imageWidth = 80;
        image_fx.setFitWidth(imageWidth);
        view_order = 20002.0;
        image_fx.setViewOrder(view_order);
        pane.getChildren().add(image_fx);
    }

    public void setUnlocked(Boolean unlocked) {
        this.unlocked = unlocked;
    }
}
