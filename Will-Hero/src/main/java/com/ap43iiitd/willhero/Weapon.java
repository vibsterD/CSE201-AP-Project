package com.ap43iiitd.willhero;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class Weapon extends GameObject{
    int level;
    int hp_damage;
    int hit_radius;

    public abstract void attack(ArrayList<GameObject> gameObjects, AnchorPane pane, double x, double y);

    public void upgrade(){

    }

    @Override
    public void collide(GameObject o1){

    }


}