package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Shuriken extends Weapon{

    // TODO: Add range limit

    public Shuriken(){
        url = "com/ap43iiitd/willhero/imageres/Sprites/Weapons/Shuriken/Shuriken.png";
        this.image_fx = new ImageView(new Image(url));
        this.hp_damage = 70;
        this.hit_radius = 2000;
        this.level = 1;
        this.position = new Position(true);
        this.getPosition().setVelocity(60, -(Math.random()*10));
    }

    @Override
    public void attack(ArrayList<GameObject> gameObjects, AnchorPane pane, double x, double y) {
        throwShuriken(gameObjects, pane, x,y);
    }

    private void throwShuriken(ArrayList<GameObject> gameObjects, AnchorPane pane, double x, double y){
        System.out.println("I am a ninja, ranged attack, go!");
        for (int i = 0; i < level; i++) {
            addAndAttack(gameObjects, pane, x, y);
        }
    }

    private void addAndAttack(ArrayList<GameObject> gameObjects, AnchorPane pane, double x, double y) {
        Weapon attack = new Shuriken();
        ImageView img_weapon = attack.getImage_fx();
        img_weapon.setPreserveRatio(true);
        imageWidth = 40;
        img_weapon.setFitWidth(imageWidth);
        img_weapon.setLayoutX(x);
        img_weapon.setLayoutY(y);
        pane.getChildren().add(img_weapon);
        gameObjects.add(attack);
    }
}
