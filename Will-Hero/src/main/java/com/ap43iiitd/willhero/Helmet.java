package com.ap43iiitd.willhero;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Helmet implements Serializable {
    ArrayList<Weapon> weapon_slots;
    Weapon current_weapon;

    public Helmet() {}

    public int hasWeapon() {
        //Hot encoding
        //0: no weapon
        //1: first weapon slot
        //2: second weapon slot
        //3: both filled
        int retval = 0;
        if (weapon_slots.get(0)!=null) retval += 1;
        if (weapon_slots.get(1)!=null) retval += 2;
        return retval;
    }

    public void setCurrent_weapon(int weapon_num) {
        if(weapon_num>1||weapon_num<0) return;
        current_weapon = weapon_slots.get(weapon_num);
        System.out.println("Weapon has been set to: "+weapon_num);
    }

    public Weapon getCurrent_weapon() {
        return current_weapon;
    }
}
