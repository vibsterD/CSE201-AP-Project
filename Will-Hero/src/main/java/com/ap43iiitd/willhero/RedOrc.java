package com.ap43iiitd.willhero;

public class RedOrc extends Orc{
    public RedOrc(Position position, Player main_hero){
        super(60, 100, position, 1+r1.nextInt(2), main_hero);
    }
}
