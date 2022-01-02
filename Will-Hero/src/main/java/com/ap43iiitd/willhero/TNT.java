package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

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
        ScaleTransition st = new ScaleTransition(Duration.millis(200), image_fx);
        st.setByX(5);
        st.setByY(5);
        st.setOnFinished(e->{
            FadeTransition ft = new FadeTransition(Duration.millis(100),image_fx);
            ft.setToValue(0);
            ft.play();
            ft.setOnFinished(ev-> {
                explode();
                visibility = false;
                image_fx.setVisible(visibility);
            });
        });
        st.play();
//        image_fx.setFitWidth(500);
        // animate increase in size
        // call explode on fineshed event
    }
    private void explode() {

        ArrayList<GameObject> in_scene = game.getIn_scene();

        for (GameObject gameObject : in_scene) {
            if (gameObject.getImage_fx().getBoundsInParent().intersects(image_fx.getBoundsInParent())) {
                if(gameObject instanceof Orc ) {
                    Orc orc = (Orc) gameObject;
                    orc.forceEliminate();
                }
                if(gameObject instanceof Player) {
                    Player player = (Player) gameObject;
                    System.out.println("TNT KILLED PLAYER");
                    player.eliminate();
                }
            }
        }

    }

    public void addToScene(AnchorPane game_screen, int x, int y, Island island) {
        imageWidth = 60;
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(imageWidth);
        view_order = 10000.0;
        image_fx.setViewOrder(view_order);

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

        if(o1 instanceof TNT && is_exploded == false) {
            TNT tnt = (TNT) o1;
            if(tnt.getImage_fx().getBoundsInParent().intersects(this.image_fx.getBoundsInParent())) {
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
