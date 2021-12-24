package com.ap43iiitd.willhero;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private double gravity;
    private int restitution;
    private Position position;

    public void collide(GameObject o1) {}
}
