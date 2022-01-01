package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeaponChest extends Chest{

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
    }
}
