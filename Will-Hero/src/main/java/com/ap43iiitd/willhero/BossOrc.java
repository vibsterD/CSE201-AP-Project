package com.ap43iiitd.willhero;

public class BossOrc extends Orc{
    public BossOrc(Position position, Player main_hero){
        super(160, 300, position, r1.nextInt(2)+3, main_hero);
    }
}
