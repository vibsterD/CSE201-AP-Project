package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class WeaponChest extends Chest{
    final static Random r1 = new Random(1337);
    public WeaponChest() {
        System.out.println("WRDDDDDDDD");
        this.closed_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (5).png");
        this.opened_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (79).png");
        System.out.println("ASDSADASDAS");
        this.image_fx = new ImageView(this.closed_chest);
        this.image_fx.setPreserveRatio(true);
    }

    @Override
    public void reward(Player player){
        // update or upgrade weapon
//        player.updateWeapon(new ThrowingKnives());
//        player.updateWeapon(new Shuriken());
        int choice = r1.nextInt(2);
        if(choice == 0) {
            // throwing knives
            player.updateWeapon(new ThrowingKnives());
        }else {
            player.updateWeapon(new Shuriken());
        }
    }
}
