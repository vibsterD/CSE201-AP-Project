package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends GameObject implements Serializable {

    private int coins;
    private Helmet helmet;
    private Boolean respawned;
    private int location;

    private Game game;
    private Boolean has_won;
    private Boolean alive = true;
//    private Boolean dash_collision;
//    private int jump_length;
//    private int jump_frequency;
//    Boolean dash_flag;

    public Player() {
        this.helmet = new Iron();
        System.out.println("Creating player");
        this.respawned = false;
        this.has_won = false;
//        this.dash_collision = false;
        this.position = new Position();
        url = "com/ap43iiitd/willhero/imageres/Sprites/Iron.png";
        Image image = new Image(url);
        this.image_fx = new ImageView(image);
        this.coins = 0;
    }

    public void addToScene(AnchorPane pane) {
        image_fx.setPreserveRatio(true);
        imageWidth = 50;
        image_fx.setFitWidth(imageWidth);
        image_fx.setLayoutX(150);
        System.out.println(pane+" irjgiwrugier");
        pane.getChildren().add(image_fx);
    }

    public Helmet getHelmet() {
        return helmet;
    }

    //    public Position getPosition() {
//        return position;
//    }


    public void setRespawned() {
        this.respawned = true;
    }

    public void not_alive(){
        this.alive = false;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive() {
        this.alive = true;
    }

    public void attack(ArrayList<GameObject> game_objects,AnchorPane game_screen) {
        Weapon att = getHelmet().getCurrent_weapon();
        if (att != null) {
            att.attack(game_objects, game_screen, getImage_fx().getTranslateX() + getImage_fx().getLayoutX(), getImage_fx().getTranslateY() + getImage_fx().getLayoutY());
        }
    }

    public void dash() {
    }

    public void updateCoins(int delta) {
        this.coins = this.coins + delta;
    }

    public int getCoins() {
        return this.coins;
    }

    public void updateWeapon(Weapon weapon) {
        helmet.rewardWeapon(weapon);
    }

    private void upgradeWeapon() {
    }

    public void eliminate() {
        System.out.println("\n\nTHE HERO DIED!!!!!!!\n\n\n");
        not_alive();
    }

    public Boolean hasRespawned() {
        return respawned;
    }

    public Boolean getDashCollision() {
        return true;
    }

    public Boolean hasWon() {
        return this.has_won;
    }

    public void setHasWon() {
        this.has_won = true;
    }

    @Override
    public void collide(GameObject o1) {

        if(image_fx.getTranslateY() > 600) {
            eliminate();
        }

        // player collid logic
        Player hero = (Player) this;
        if (o1 instanceof Island) {
            // island


            Island island = (Island) o1;
            if (hero.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                // hero
                // island
                double height = island.getImage_fx().getFitHeight() / 2;
                if (hero.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
//                    System.out.println("Collided with island");
                    hero.getPosition().setVelocity(hero.getPosition().getVel_x(), -10);
                }


                // Hero Island

                double island_min_x = island.getImage_fx().getBoundsInParent().getMinX();
                double hero_max_x = hero.getImage_fx().getBoundsInParent().getMaxX();
                double x_del_right = Math.abs(island_min_x - hero_max_x);
//                System.out.println("XDEL: " + x_del_right);
                if(x_del_right < 50.0) {
//                    System.out.println("COLLIDING ORC");
                    position.setVelocity(0, position.getVel_y());

                }


            }



        }


        if (o1 instanceof Orc) {
            // orc
            Orc orc2 = (Orc) o1;

            if(orc2.getDead()) {
                return;
            }

            if(orc2.getImage_fx().getTranslateY() > 500) {
                // kill Orc and reward player with some coins
                orc2.forceEliminate();
                updateCoins(5);
                return;
            }

            if (orc2.getImage_fx().getBoundsInParent().intersects(hero.getImage_fx().getBoundsInParent())) {
//                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
                double orc_max_y = orc2.getImage_fx().getBoundsInParent().getMaxY();
                double hero_min_y = hero.getImage_fx().getBoundsInParent().getMinY();
                double y_del = Math.abs(orc_max_y - hero_min_y);

                // orc
                // hero
                // checking if up/down
                if (y_del < 10.0) {
                    // orc is above hero
                    orc2.getPosition().setVelocity(0, 0);
                    hero.getPosition().setVelocity(0, 0);
                    orc2.killPlayer(hero);
                    System.out.println("**** DEAD");
                    return;
                }

                // hero
                // orc

                double orc_min_y = orc2.getImage_fx().getBoundsInParent().getMinY();
                double hero_max_y = hero.getImage_fx().getBoundsInParent().getMaxY();
                double y_del_down = Math.abs(orc_min_y - hero_max_y);

                if (y_del_down < 10.0) {
                    //                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
//                    System.out.println("HELLLO");
                    hero.getPosition().setVelocity(hero.getPosition().getVel_x(), -5);
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
                    position.setVelocity(3, position.getVel_y());
                    if(orc2 instanceof BossOrc) {
                        orc2.getPosition().setVelocity(10, -1);
                    }else {
                        orc2.getPosition().setVelocity(30, -1);

                    }
//                    this.dash_collision = true;
                }


            }


        }

        if(o1 instanceof PowerUpBrick) {
            PowerUpBrick brick = (PowerUpBrick) o1;
            if(brick.getImage_fx().getBoundsInParent().intersects(image_fx.getBoundsInParent())) {
                double brick_max_y = brick.getImage_fx().getBoundsInParent().getMaxY();
                double hero_min_y = hero.getImage_fx().getBoundsInParent().getMinY();
                double y_del = Math.abs(brick_max_y - hero_min_y);

                // orc
                // hero
                // checking if up/down
                if (y_del < 10.0) {
                    // orc is above hero
                    System.out.println("WTGFGFFFFFF");
                    hero.getPosition().setVelocity(0, 3);
                    brick.reward();
                    return;
                }

                // Hero brick

                double brick_min_x = brick.getImage_fx().getBoundsInParent().getMinX();
                double hero_max_x = hero.getImage_fx().getBoundsInParent().getMaxX();
                double x_del_right = Math.abs(brick_min_x - hero_max_x);
//                System.out.println("XDEL: " + x_del_right);
                if(x_del_right < 50.0) {
//                    System.out.println("COLLIDING ORC");
                    position.setVelocity(0, position.getVel_y());

                }
            }
        }
    }

//    public Boolean getDash_collision() {
//        return dash_collision;
//    }
}
