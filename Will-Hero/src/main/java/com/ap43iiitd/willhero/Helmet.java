package com.ap43iiitd.willhero;

import java.io.Serializable;

public abstract class Helmet implements Serializable {
    private Weapon current_weapon;

    public Helmet() {}

    public Boolean hasWeapon() {
        return true;
    }
}
