package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class Chest extends GameObject implements Serializable {
    final static Random r1 = new Random(1337);
    private Boolean isEmpty = false;
    transient protected Image closed_chest;
    transient protected ArrayList<Image> transition_chests;

    private final Position position;
    protected String open_chest_url = "com/ap43iiitd/willhero/imageres/Sprites/Chests/ (75).png";

    public Chest() {
        transition_chests = new ArrayList<Image>();
        position = new Position(0);
    }

    @Override
    public void collide(GameObject o1){
        if(o1 instanceof Player && !isEmpty) {
            Player player = (Player) o1;
            if(player.getImage_fx().getBoundsInParent().intersects(this.image_fx.getBoundsInParent())) {
                // chest open
                url = open_chest_url;
                System.out.println("Found Chest");
                image_fx.setImage(new Image(open_chest_url));
                if(this instanceof CoinChest) {
                    image_fx.setLayoutY(image_fx.getLayoutY() + 15.0);
                }
                isEmpty = true;
                reward(player);
            }
        }
    }

    public void addToScene(AnchorPane game_screen, int x, int y, Island island) {
        this.imageWidth = 60;

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
    public Position getPosition() {
        return position;
    }

    public abstract void reward(Player player);
}
