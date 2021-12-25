package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
//    Boolean dash_flag;

    public Player() {
        this.helmet = new Iron();
        System.out.println("Creating player");
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
        this.position = new Position();
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Iron.png");
        this.image_fx = new ImageView(image);
    }

    public Player(Boolean respawned, Position position, int location, Weapon weapon) {
        this.respawned = false;
        this.is_eliminated = false;
        this.has_won = false;
        this.dash_collision = false;
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Iron.png");
        this.image_fx = new ImageView(image);
    }

    public void addToScene(AnchorPane pane) {
        image_fx.setScaleX(0.5);
        image_fx.setScaleY(0.5);
        image_fx.setLayoutX(150);
        image_fx.setLayoutY(40);
        pane.getChildren().add(image_fx);
    }

    public Helmet getHelmet() {
        return helmet;
    }

    //    public Position getPosition() {
//        return position;
//    }

    public void jump() {
    }

    private void attack() {
    }

    public void dash() {
    }

    public void updateCoins(int delta) {
    }

    public int getCoins() {
        return this.coins;
    }

    public void updateWeapon(Weapon weapon) {
    }

    private void upgradeWeapon() {
    }

    public void eliminate() {
    }

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

    @Override
    public void collide(GameObject o1) {
        // player collid logic
        Player hero = (Player) this;
        if (o1 instanceof Island) {
            // island

            Island island = (Island) o1;
            if (hero.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                double height = island.getImage_fx().getFitHeight() / 2;
                if (hero.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
//                    System.out.println("Collided with island");
                    hero.getPosition().setVelocity(0, -10);
                }
            }

        }


        if (o1 instanceof Orc) {
            // orc
            Orc orc2 = (Orc) o1;

            if (orc2.getImage_fx().getBoundsInParent().intersects(hero.getImage_fx().getBoundsInParent())) {
//                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
                double orc_max_y = orc2.getImage_fx().getBoundsInParent().getMaxY();
                double hero_min_y = hero.getImage_fx().getBoundsInParent().getMinY();
                double y_del = Math.abs(orc_max_y - hero_min_y);

                // checking if up/down
                if (y_del < 10.0) {
                    // orc is above hero
                    orc2.getPosition().setVelocity(0, 0);
                    hero.getPosition().setVelocity(0, 0);
                    System.out.println("FUCKING DEAD");
                    return;
                }

                double orc_min_y = orc2.getImage_fx().getBoundsInParent().getMinY();
                double hero_max_y = hero.getImage_fx().getBoundsInParent().getMaxY();
                double y_del_down = Math.abs(orc_min_y - hero_max_y);

                if (y_del_down < 10.0) {
                    //                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
//                    System.out.println("HELLLO");
                    hero.getPosition().setVelocity(0, -5);
                    return;
                }

                // Orc Hero

                double orc_max_x = orc2.getImage_fx().getBoundsInParent().getMaxX();
                double hero_min_x = hero.getImage_fx().getBoundsInParent().getMinX();
                double x_del = Math.abs(orc_max_x - hero_min_x);

                if(x_del < 10.0) {
                    System.out.println("Orc Hero SUPER RARE!!");

                }

                // Hero Orc

                double orc_min_x = orc2.getImage_fx().getBoundsInParent().getMinX();
                double hero_max_x = hero.getImage_fx().getBoundsInParent().getMaxX();
                double x_del_right = Math.abs(orc_min_x - hero_max_x);
//                System.out.println("XDEL: " + x_del_right);
                if(x_del_right < 50.0) {
//                    System.out.println("COLLIDING ORC");
                    orc2.getPosition().setVelocity(30, -1);
                    this.dash_collision = true;
                }


            }


        }
    }

    public Boolean getDash_collision() {
        return dash_collision;
    }
}
