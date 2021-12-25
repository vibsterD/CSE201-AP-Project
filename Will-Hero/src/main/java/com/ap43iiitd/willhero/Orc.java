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
    private final String color;
    private final int size;
    private int hp;
    private String state = "sleep";


    public Orc(String color, int size, int hp, Position position){
        this.color = color;
        this.size = size;
        this.hp = hp;
        this.position = position;
        // ../../../../resources/com/ap43iiitd/willhero/imageres/
//        System.out.println("UPPPWER as asd asd asd ");
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Orcs/6.png");
//        System.out.println("asdnalsndlasndlksanld");
        this.image_fx = new ImageView(image);
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

//    public Position getPosition() {
//        return position;
//    }

    public void addToScene(AnchorPane pane) {
        image_fx.setScaleX(0.7);
        image_fx.setScaleY(0.7);
        image_fx.setLayoutX(220);
        image_fx.setLayoutY(400);
        pane.getChildren().add(image_fx);
    }

    @Override
    public void collide(GameObject o1) {
        Orc orc2 = (Orc) this;
        if (o1 instanceof Island) {
            Island island = (Island) o1;
            if (orc2.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                double height = island.getImage_fx().getFitHeight() / 2;
                if (orc2.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
                    System.out.println("Collided with island");
                    orc2.getPosition().setVelocity(0, -5);
                }

            }

        }
    }
}
