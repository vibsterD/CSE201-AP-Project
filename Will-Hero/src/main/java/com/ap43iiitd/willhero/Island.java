package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class Island extends GameObject implements Serializable {
    @FXML
    private ImageView island_fx;
    private Position position;
    private int restitution;




    public Island(int restitution, Position pos) {
        this.position = pos;
        this.restitution = restitution;

        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Islands/T_Islands_01.png");
        this.island_fx = new ImageView(image);
    }


    public ImageView getIsland_fx() {
        return island_fx;
    }

    public void addToScene(AnchorPane pane) {
//        island_fx.setScaleX(0.2);
//        island_fx.setScaleY(0.2);
        island_fx.setPreserveRatio(true);
        island_fx.setLayoutX(114);
        island_fx.setLayoutY(472);
        island_fx.setFitWidth(200);
//        island_fx.setFitHeight(150);
        pane.getChildren().add(island_fx);
    }

}
