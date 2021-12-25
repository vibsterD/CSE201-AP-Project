package com.ap43iiitd.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.Random;

public class Orc extends GameObject implements Serializable {
    final static Random r1 = new Random(786);
    @FXML
    private final String color;
    private final int size;
    private int hp;
    private String state = "sleep";


    public Orc(String color, int size, int hp, Position position){
        this.color = color;
        this.size = size;
        this.hp = hp;
        this.position = position;
        System.out.println("CREATING ORC");
        // ../../../../resources/com/ap43iiitd/willhero/imageres/
//        System.out.println("UPPPWER as asd asd asd ");
        int orc_num = 1+r1.nextInt(4);
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Orcs/O (".concat(String.valueOf(orc_num)).concat(").png"));
        this.image_fx = new ImageView(image);
    }

    public void jump(){

    }

    private void killPlayer(Player player){

    }

    public int getHp(){
        return hp;
    }

    private void setHp(int hp) {
        this.hp = hp;
    }

    private void die(){

    }

//    public Position getPosition() {
//        return position;
//    }

    public void addToScene(AnchorPane pane, int x, int y) {
        image_fx.setPreserveRatio(true);
//        image_fx.setScaleX(0.5);
//        image_fx.setScaleY(0.5);
        image_fx.setFitWidth(70);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        pane.getChildren().add(image_fx);
    }

    @Override
    public void collide(GameObject o1) {
        Orc orc2 = (Orc) this;
        if (o1 instanceof Island) {
            Island island = (Island) o1;
            if (orc2.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                double height = island.getImage_fx().getFitHeight() / 2;
                if (orc2.getPosition().getVel_y() > 0) {
//                    hero.getHero_fx().setTranslateY(hero.getHero_fx().getTranslateY() + height);
//                    if(hero.getHero_fx().getBoundsInParent().intersects(island.getIsland_fx().getBoundsInParent()))
                    System.out.println("Collided with island");
                    orc2.getPosition().setVelocity(0, -5);
                }

            }

        }

        if(o1 instanceof Orc) {
            Orc orc1 = (Orc) o1;
            if(orc1.getImage_fx().getBoundsInParent().intersects(orc2.getImage_fx().getBoundsInParent())) {
                double orc_min_y = orc2.getImage_fx().getBoundsInParent().getMinY();
                double orc1_max_y = orc1.getImage_fx().getBoundsInParent().getMaxY();
                double y_del_down = Math.abs(orc_min_y - orc1_max_y);

                if (y_del_down < 10.0) {
                    //                System.out.println( "Orc : " + orc2.getOrc_fx().getBoundsInParent().getMaxY());
//                System.out.println( "Hero: " + hero.getHero_fx().getBoundsInParent().getMinY());
//                    System.out.println("HELLLO");
                    orc1.getPosition().setVelocity(0, -5);
                    return;
                }

                // orc1 orc2

                double orc_min_x = orc2.getImage_fx().getBoundsInParent().getMinX();
                double orc1_max_x = orc1.getImage_fx().getBoundsInParent().getMaxX();
                double x_del_right = Math.abs(orc_min_x - orc1_max_x);
//            System.out.println("XDEL: " + x_del_right);
                if (x_del_right < 50.0) {
                    System.out.println("COLLIDING ORC ORC");
                    orc2.getPosition().setVelocity(50, -1);
//                orc1.dash_collision = true;
                }
            }
        }
    }
}
