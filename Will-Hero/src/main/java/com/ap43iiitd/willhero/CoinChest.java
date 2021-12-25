package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{

    public CoinChest() {
        System.out.println("WRDDDDDDDD");
        this.closed_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (2).png");
        this.opened_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (75).png");
        System.out.println("ASDSADASDAS");
        this.image_fx = new ImageView(this.closed_chest);
        this.image_fx.setPreserveRatio(true);
    }

    @Override
    public void reward(Player player){
//        this.chest.setImage(this.opened_chest);
        player.updateCoins(10);
    }


}
