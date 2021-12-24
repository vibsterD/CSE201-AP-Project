package com.ap43iiitd.willhero;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {
    private int coins;
    private Helmet helmet;
    private Boolean respawned;
    private int location;
    private Boolean is_eliminated;
    private Game game;
    private Boolean has_won;
    private Boolean dash_collision;
    private int jump_length;
    private int jump_frequency;

    public Player() {
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
    }

    public Player(Boolean respawned, Position position, int location, Weapon weapon) {
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
    }

    public void jump() {}
    private void attack() {}
    public void dash() {}
    public void updateCoins(int delta) {}
    public int getCoins() {
        return this.coins;
    }
    public void updateWeapon(Weapon weapon) {}
    private void upgradeWeapon() {}
    public void eliminate() {}
    public Boolean hasRespawned() {
        return true;
    }
    public Boolean getDashCollision() {
        return true;
    }
    public Boolean hasWon() {
        return this.has_won;
    }
    public Boolean eliminated() {
        return this.is_eliminated;
    }
    public void setHasWon() {
        this.has_won = true;
    }

}
