package com.ap43iiitd.willhero;

import java.io.Serializable;

public abstract class Chest extends GameObject implements Serializable {
    private Boolean isEmpty = false;

    @Override
    public void collide(GameObject o1){

    }

    public abstract void reward(Player player)
}
