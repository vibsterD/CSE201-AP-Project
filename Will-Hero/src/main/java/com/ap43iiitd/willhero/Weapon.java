package com.ap43iiitd.willhero;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public abstract class Weapon extends GameObject{
    int level;
    int hp_damage;
    int hit_radius;

    public abstract void attack(ArrayList<GameObject> gameObjects, AnchorPane pane, double x, double y);

    public void upgrade(){
        level++;
        hp_damage += 5;
    }

    @Override
    public void collide(GameObject o1){
        if(o1 instanceof  Orc) {
            Orc orc = (Orc) o1;
            if(orc.getImage_fx().getBoundsInParent().intersects(this.getImage_fx().getBoundsInParent())) {
                orc.setHp(orc.getHp() - hp_damage);
                orc.eliminate();
            }
        }else if(o1 instanceof TNT) {
            TNT tnt = (TNT) o1;
            if(tnt.getImage_fx().getBoundsInParent().intersects(image_fx.getBoundsInParent())) {
                tnt.activate();
            }
        }
    }


}