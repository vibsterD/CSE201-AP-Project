package com.ap43iiitd.willhero;

public class GreenOrc extends Orc{
    public GreenOrc(Position position, Player main_hero){
        super(60, 100, position, r1.nextInt(2)+3, main_hero);
    }
}
