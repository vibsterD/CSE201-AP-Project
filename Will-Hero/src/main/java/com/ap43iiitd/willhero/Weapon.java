package com.ap43iiitd.willhero;

public abstract class Weapon extends GameObject{
    int level;
    int hp_damage;
    int hit_radius;

    public abstract void attack();

    public void upgrade(){

    }

    @Override
    public void collide(GameObject o1){

    }


}