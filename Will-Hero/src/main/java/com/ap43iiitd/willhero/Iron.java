package com.ap43iiitd.willhero;

import java.io.Serializable;
import java.util.ArrayList;

public class Iron extends Helmet implements Serializable {
    //uses default constructor with nothing
    //doing below for sake

    //TODO: REMOVE
    public Iron(){
        weapon_slots = new ArrayList<Weapon>(2);

        weapon_slots.add(null);
        weapon_slots.add(null);

        weapon_slots.set(0,new ThrowingKnives()); //usually null
        weapon_slots.set(1,new Shuriken()); //usually null

    }

}
