package com.ap43iiitd.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;

public class Orc extends GameObject implements Serializable {
    @FXML
    private ImageView orc_fx;
    private final String color;
    private final int size;
    private int hp;
    private String state = "sleep";
    private Position position;

    public Orc(String color, int size, int hp, Position position){
        this.color = color;
        this.size = size;
        this.hp = hp;
        this.position = position;
        // ../../../../resources/com/ap43iiitd/willhero/imageres/
//        System.out.println("UPPPWER as asd asd asd ");
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Orcs/6.png");
//        System.out.println("asdnalsndlasndlksanld");
        this.orc_fx = new ImageView(image);
    }

    public void jump(){

    }

    private void killPlayer(Player player){

    }

    public int getHp(){
        return hp;
    }

    private void setHp(int hp) {
        this.hp = hp;
    }

    private void die(){

    }

    @Override
    public void collide(GameObject o1){

    }

    public void update(){
        orc_fx.setTranslateX(orc_fx.getTranslateX()+2);
    }

    public void addToScene(AnchorPane pane) {
        orc_fx.setScaleX(0.7);
        orc_fx.setScaleY(0.7);
        orc_fx.setLayoutX(1400);
        orc_fx.setLayoutY(200);
        pane.getChildren().add(orc_fx);
    }
}
