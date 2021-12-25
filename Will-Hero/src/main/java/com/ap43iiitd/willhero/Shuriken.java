package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shuriken extends Weapon{

    public Shuriken(){
        this.image_fx = new ImageView(new Image("com/ap43iiitd/willhero/imageres/Sprites/Weapons/Shuriken/Shuriken.png"));
        this.hp_damage = 100;
        this.hit_radius = 2000;
        this.level = 1;
    }

    @Override
    public void attack() {
        throwShuriken();
    }

    private void throwShuriken(){

    }
}
