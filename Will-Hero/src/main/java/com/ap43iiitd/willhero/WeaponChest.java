package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class WeaponChest extends Chest{
    final static Random r1 = new Random(1337);
    public WeaponChest() {
        url = "com/ap43iiitd/willhero/imageres/Sprites/Chests/ (5).png";
        this.closed_chest = new Image(url);
        open_chest_url = "com/ap43iiitd/willhero/imageres/Sprites/Chests/ (79).png";
        this.image_fx = new ImageView(this.closed_chest);
        this.image_fx.setPreserveRatio(true);
    }

    @Override
    public void reward(Player player){

        int choice = r1.nextInt(2);
        if(choice == 0) {
            // throwing knives
            player.updateWeapon(new ThrowingKnives());
        }else {
            player.updateWeapon(new Shuriken());
        }
    }
}
