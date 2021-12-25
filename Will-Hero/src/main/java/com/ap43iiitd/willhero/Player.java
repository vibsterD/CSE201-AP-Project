package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class Player extends GameObject implements Serializable {
    @FXML
    private ImageView hero_fx;
    private Position position;
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
//    Boolean dash_flag;

    public Player() {
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
        this.position = new Position();
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Iron.png");
        this.hero_fx = new ImageView(image);
    }

    public Player(Boolean respawned, Position position, int location, Weapon weapon) {
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Iron.png");
        this.hero_fx = new ImageView(image);
    }

    public void addToScene(AnchorPane pane) {
        hero_fx.setScaleX(0.5);
        hero_fx.setScaleY(0.5);
        hero_fx.setLayoutX(174);
        hero_fx.setLayoutY(100);
        pane.getChildren().add(hero_fx);
    }

    public ImageView getHero_fx() {
        return hero_fx;
    }

    public Position getPosition() {
        return position;
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
