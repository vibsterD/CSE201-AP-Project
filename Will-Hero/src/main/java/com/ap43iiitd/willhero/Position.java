package com.ap43iiitd.willhero;

public class Position implements Serializable{
    private int x;
    private int y;
    private int delta_x;
    private int delta_y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void updatePosition() {}
    public void setDelta(int delta_x, int delta_y) {
        this.delta_x = delta_x;
        this.delta_y = delta_y;
    }
}
