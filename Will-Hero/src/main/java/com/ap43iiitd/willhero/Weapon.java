package com.ap43iiitd.willhero;

public abstract class Weapon extends GameObject{
    private int level;
    private int hp_damage;
    private int hit_radius;

    public abstract void attack();

    public void upgrade(){

    }

    @Override
    public void collide(GameObject o1){

    }


}