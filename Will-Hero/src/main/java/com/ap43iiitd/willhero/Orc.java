package com.ap43iiitd.willhero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;
import java.util.Random;

public abstract class Orc extends GameObject implements Serializable {
    final static Random r1 = new Random(786);
    private final int size;
    private int hp;
    private Boolean collidable;
    private Boolean dead;

    public Orc(int size, int hp, Position position, int res_num){
        this.size = size;
        this.hp = hp;
        this.position = position;
        this.collidable = true;
//        System.out.println("CREATING ORC");
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Orcs/O (".concat(String.valueOf(res_num)).concat(").png"));
        this.image_fx = new ImageView(image);
        dead = false;
    }

    public void jump(){

    }

    public void killPlayer(Player player){
        System.out.println("Oh no, our player, it's dead");
        player.not_alive();

    }

    public int getHp(){
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    private void die(){
        System.out.println("ORC DIED");
//        image_fx.setVisible(false);
//        image_fx.setFitWidth(20);
//        image_fx.setVisible(false);
        collidable = false;
    }


    public void eliminate() {
        if(hp > 0) {
            return;
        }
        dead = true;
        die();
    }

    public void forceEliminate() {
        dead = true;
        die();
    }

    public Boolean getDead() {
        return dead;
    }

    public void addToScene(AnchorPane pane, int x, int y) {
        image_fx.setPreserveRatio(true);
        image_fx.setFitWidth(size);
        image_fx.setLayoutX(x);
        image_fx.setLayoutY(y);
        pane.getChildren().add(image_fx);
    }

    @Override
    public void collide(GameObject o1) {
        if(collidable) {
            Orc orc2 = (Orc) this;
            if (o1 instanceof Island) {
                Island island = (Island) o1;
                if (orc2.getImage_fx().getBoundsInParent().intersects(island.getImage_fx().getBoundsInParent())) {
                    double height = island.getImage_fx().getFitHeight() / 2;
                    if (orc2.getPosition().getVel_y() > 0) {
//                    System.out.println("Collided with island");
                        orc2.getPosition().setVelocity(0, -5);
                    }

                }

            }

            if (o1 instanceof Orc) {
                Orc orc1 = (Orc) o1;
                if (orc1.getImage_fx().getBoundsInParent().intersects(orc2.getImage_fx().getBoundsInParent())) {
                    double orc_min_y = orc2.getImage_fx().getBoundsInParent().getMinY();
                    double orc1_max_y = orc1.getImage_fx().getBoundsInParent().getMaxY();
                    double y_del_down = Math.abs(orc_min_y - orc1_max_y);

                    if (y_del_down < 10.0) {
                        orc1.getPosition().setVelocity(0, -5);
                        return;
                    }

                    // orc1 orc2

                    double orc_min_x = orc2.getImage_fx().getBoundsInParent().getMinX();
                    double orc1_max_x = orc1.getImage_fx().getBoundsInParent().getMaxX();
                    double x_del_right = Math.abs(orc_min_x - orc1_max_x);

                    if (x_del_right < 50.0) {
//                    System.out.println("COLLIDING ORC ORC");
                        orc2.getPosition().setVelocity(50, -1);

                    }
                }
            }
        }
    }
}
