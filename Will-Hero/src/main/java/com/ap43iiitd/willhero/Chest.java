package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Chest extends GameObject implements Serializable {
    private Boolean isEmpty = false;
    protected Image closed_chest;
    protected ArrayList<Image> transition_chests;
    protected Image opened_chest;
    protected ImageView chest;
    private Position position;

    public Chest() {
        transition_chests = new ArrayList<Image>();
    }

    @Override
    public void collide(GameObject o1){
        if(o1 instanceof Island) {
            Island island = (Island) o1;
            double y_max = island.getImage_fx().getBoundsInParent().getMinY();

        }

        if(o1 instanceof Player) {

        }
    }

    public void addToScene(AnchorPane game_screen, int x, int y, ArrayList<Island> islands) {
        chest.setPreserveRatio(true);
        chest.setFitWidth(60);
        chest.setViewOrder(10000);
        chest.setTranslateX(x);
        chest.setScaleY(350);
        game_screen.getChildren().add(chest);
        for(int i = 0; i < islands.size(); i++) {

        }
    }

    public abstract void reward(Player player);
}
