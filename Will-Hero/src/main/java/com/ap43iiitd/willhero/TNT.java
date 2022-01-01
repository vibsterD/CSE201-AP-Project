package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Random;

public class TNT extends Obstacle{
    final static Random r1 = new Random(13370);
    private Position position;
    private Boolean is_exploded;
    private double explosion_radius;
    //Image explosion;
    Game game;

    public TNT(Game game) {
        url = "com/ap43iiitd/willhero/imageres/Sprites/TNT.png";
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
        this.position = new Position(0);
        this.is_exploded = false;
        this.game = game;
        this.explosion_radius = 300;
    }

    public void activate() {
        image_fx.setFitWidth(100);
        explode();
    }
    private void explode() {
//        if(o1 instanceof Player) {
//            ((Player) o1).eliminate();
//        }else {
//            // Orc
//            ((Orc) o1).eliminate();
//        }

        ArrayList<GameObject> in_scene = game.getIn_scene();

        double tnt_x = image_fx.getBoundsInParent().getCenterX();
        double tnt_y = image_fx.getBoundsInParent().getCenterY();

        for (GameObject gameObject : in_scene) {
            if (gameObject instanceof Player) {
                Player player = (Player) gameObject;
                double player_x = player.getImage_fx().getBoundsInParent().getCenterX();
                double player_y = player.getImage_fx().getBoundsInParent().getCenterY();
                double distance = Math.sqrt((player_x - tnt_x) * (player_x - tnt_x) + (player_y - tnt_y) * (player_y - tnt_y));
                if (distance < explosion_radius) {
                    player.eliminate();
                }
            }

            if (gameObject instanceof Orc) {
                Orc orc = (Orc) gameObject;
                double orx_x = orc.getImage_fx().getBoundsInParent().getCenterX();
                double orc_y = orc.getImage_fx().getBoundsInParent().getCenterY();
                double distance = Math.sqrt((orx_x - tnt_x) * (orx_x - tnt_x) + (orc_y - tnt_y) * (orc_y - tnt_y));
                if (distance < explosion_radius) {
                    orc.eliminate();
                }
            }
        }

    }

    public void addToScene(AnchorPane game_screen, int x, int y, Island island) {
        imageWidth = 60;
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(imageWidth);
        image_fx.setViewOrder(10000);

        image_fx.setLayoutX(x + r1.nextInt(100));
        image_fx.setLayoutY(y);

        game_screen.getChildren().add(image_fx);

        double y_delta = island.getImage_fx().getBoundsInParent().getMinY() - image_fx.getBoundsInParent().getMaxY();
        image_fx.setLayoutY(image_fx.getLayoutY() + y_delta);
    }

    @Override
    public void collide(GameObject o1) {
        if(o1 instanceof Player && is_exploded == false) {
            Player player = (Player) o1;
            if(player.getImage_fx().getBoundsInParent().intersects(this.image_fx.getBoundsInParent())) {
                // chest open
                System.out.println("TNTNTNTNTNT");
//                image_fx.setImage(opened_chest);
                activate();
                is_exploded = true;
            }
        }

        if(o1 instanceof Orc && is_exploded == false) {
            Orc orc = (Orc) o1;
            if(orc.getImage_fx().getBoundsInParent().intersects(this.image_fx.getBoundsInParent())) {
                // chest open
                System.out.println("TNTNTNNTN");
//                image_fx.setImage(opened_chest);
                activate();
                is_exploded = true;
            }
        }
    };

    @Override
    public Position getPosition() {
        return position;
    }
}
