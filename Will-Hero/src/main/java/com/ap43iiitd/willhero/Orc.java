package com.ap43iiitd.willhero;

import java.io.Serializable;

public class Orc extends GameObject implements Serializable {
    private final String color;
    private final int size;
    private int hp;
    private String state = "sleep";
    private Position position;

    public Orc(String color, int size, int hp, Position position){
        this.color = color;
        this.size = size;
        this.hp = hp;
        this.position = position;
    }

    public void jump(){

    }

    private void killPlayer(Player player){

    }

    public int getHp(){
        return hp;
    }

    private void setHp(int hp) {
        this.hp = hp;
    }

    private void die(){

    }

    @Override
    public void collide(GameObject o1){

    }


}
