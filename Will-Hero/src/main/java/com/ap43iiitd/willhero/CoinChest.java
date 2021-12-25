package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoinChest extends Chest{
    @Override
    public void reward(Player player){
        this.closed_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (5).png");
        this.opened_chest = new Image("com/ap43iiitd/willhero/imageres/Sprites/Chests/ (79).png");
        this.chest = new ImageView(this.closed_chest);
    }


}
