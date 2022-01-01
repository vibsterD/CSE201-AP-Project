package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{

    public CoinChest() {
        System.out.println("WRDDDDDDDD");
        url = "com/ap43iiitd/willhero/imageres/Sprites/Chests/ (2).png";
        this.closed_chest = new Image(url);
        open_chest_url = "com/ap43iiitd/willhero/imageres/Sprites/Chests/ (75).png";
        this.opened_chest = new Image(open_chest_url);
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
