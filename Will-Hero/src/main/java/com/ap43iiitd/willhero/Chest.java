package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Chest extends GameObject implements Serializable {
    final static Random r1 = new Random(1337);
    private Boolean isEmpty = false;
    protected Image closed_chest;
    protected ArrayList<Image> transition_chests;
    protected Image opened_chest;
//    protected ImageView image_fx;
    private Position position;

    public Chest() {
        transition_chests = new ArrayList<Image>();
        position = new Position(0);
    }

    @Override
    public void collide(GameObject o1){
        if(o1 instanceof Player && isEmpty == false) {
            Player player = (Player) o1;
            if(player.getImage_fx().getBoundsInParent().intersects(this.image_fx.getBoundsInParent())) {
                // chest open
                System.out.println("CHESTTTT");
                image_fx.setImage(opened_chest);
                if(this instanceof CoinChest) {
                    image_fx.setLayoutY(image_fx.getLayoutY() + 15.0);
                }
                isEmpty = true;
                reward(player);
            }
        }
    }

    public void addToScene(AnchorPane game_screen, int x, int y, Island island) {
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(60);
        image_fx.setViewOrder(10000);

        double delta;
        double delta_min = 100000;
        image_fx.setLayoutX(x + r1.nextInt(100));
        image_fx.setLayoutY(y);
//        chest.setLayoutX(150);
//        chest.setLayoutY(400);
        game_screen.getChildren().add(image_fx);

        double y_delta = island.getImage_fx().getBoundsInParent().getMinY() - image_fx.getBoundsInParent().getMaxY();
        y_delta = 0;
        image_fx.setLayoutY(image_fx.getLayoutY() + y_delta);
        System.out.println("ADASADASQWEQWE");
//        for(int i = 0; i < islands.size(); i++) {
//            delta = Math.abs(islands.get(i).getImage_fx().getTranslateX() - x);
//            if(delta < 100.0 && delta < delta_min) {
//                delta_min = delta;
//                chest.setTranslateX(islands.get(i).getImage_fx().getTranslateX());
//            }
//        }



    }

    @Override
    public Position getPosition() {
        return position;
    }

    public abstract void reward(Player player);
}
