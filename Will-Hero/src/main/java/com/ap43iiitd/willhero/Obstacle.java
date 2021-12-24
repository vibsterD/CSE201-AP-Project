package com.ap43iiitd.willhero;

public abstract class Obstacle extends GameObject {

    public void kill(Player player) {}
    private Boolean playerIsKillable() {
        return true;
    }
}
