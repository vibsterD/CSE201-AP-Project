package com.ap43iiitd.willhero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class GameObject implements Serializable {
    private double gravity;
    protected ImageView image_fx;
    private int restitution;
    private Position position;

    public void collide(GameObject o1) {
//        System.out.println(this.getClass());
        if (this instanceof Player) {
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
                        System.out.println("Collided with island");
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
                    }

                    double orc_min_y = orc2.getImage_fx().getBoundsInParent().getMinY();
                    double hero_max_y = hero.getImage_fx().getBoundsInParent().getMaxY();
                    double y_del_down = Math.abs(orc_min_y - hero_max_y);

                    if (y_del_down < 10.0) {
                        //                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
                        System.out.println("HELLLO");
                        hero.getPosition().setVelocity(0, -10);
                    }
                }
            }

        }
        else if (this instanceof Orc) {
            // orc collide logic
//            System.out.println("ORRRRCCCC");
            Orc orc2 = (Orc) this;
            if (o1 instanceof Island) {
                Island island = (Island) o1;
                if (orc2.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                    double height = island.getImage_fx().getFitHeight() / 2;
                    if (orc2.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
                        System.out.println("Collided with island");
                        orc2.getPosition().setVelocity(0, -10);
                    }

                }

            }

        }
        else{
//            System.out.println(this.getClass());
        }

    }

    public ImageView getImage_fx() {
        return image_fx;
    }
}



