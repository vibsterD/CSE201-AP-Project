package com.ap43iiitd.willhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable {
    final static Random r1 = new Random(1337);
    AnchorPane game_screen;
    ArrayList<GameObject> game_objects;
    ArrayList<Island> islands;
    ArrayList<Orc> orcs;
    ArrayList<Chest> chests;
    ArrayList<TNT> tnts;
    ArrayList<GameObject> in_scene;
    Player hero;
    private Timeline collisionMan;
    private Rectangle sword;
    private Rectangle shuriken;

    public Game(AnchorPane game_screen, Rectangle sword, Rectangle shuriken) {
        this.game_screen = game_screen;
        game_objects = new ArrayList<GameObject>();
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        chests = new ArrayList<Chest>();
        tnts = new ArrayList<TNT>();
        initialize_game();
        this.sword = sword;
        this.shuriken = shuriken;
    }


    public void play() {
        Game game = this;
        Timeline screen_mover = new Timeline((new KeyFrame(Duration.millis(1500), event -> {
                //get gamescreen x_pos
                //get hero x_pos
                //if sum >= threshold -- shift game_screen

                double gsp = game_screen.getTranslateX();
                double sum = hero.getImage_fx().getTranslateX()+hero.getImage_fx().getLayoutX()+gsp;
                if(sum>300||sum<0){
                    // shift game_screen by sum
                    TranslateTransition gst = new TranslateTransition(Duration.millis(1500), game_screen);
                    gst.setCycleCount(1);
                    gst.setByX(-sum);
                    gst.play();

                }
            })));
            screen_mover.setCycleCount(Timeline.INDEFINITE);
            screen_mover.play();



            collisionMan = new Timeline(new KeyFrame(Duration.millis(5), event->{

                if(!hero.getAlive()) {collisionMan.pause(); gameOver(); return;}

                ArrayList <GameObject> in_scene = new ArrayList<GameObject>();
                double x_pos = hero.getImage_fx().getTranslateX();
                for (GameObject game_object : game_objects) {
                    if (Math.abs(game_object.getImage_fx().getBoundsInParent().getCenterX() - x_pos) < 1500) {
                        // in scene
                        in_scene.add(game_object);
                    }
                }
    //            System.out.println("inscene: " + in_scene.size());
                // Temp line
                game.setIn_scene(in_scene);
                // Temp line
                for(int i = 0; i < in_scene.size(); i++) {
                    in_scene.get(i).getPosition().updatePosition(in_scene.get(i).getImage_fx());
                    for(int j = i + 1; j < in_scene.size(); j++) {

                        in_scene.get(i).collide(in_scene.get(j));
                        in_scene.get(j).collide(in_scene.get(i));
                    }
                }

                int ungray_weapons = hero.getHelmet().hasWeapon();
                if(ungray_weapons==1){
                    sword.setFill(Color.valueOf("d2ff26"));
                }
                else if (ungray_weapons==2){
                    shuriken.setFill(Color.valueOf("d2ff26"));
                }
                else if (ungray_weapons==3){
                    sword.setFill(Color.valueOf("d2ff26"));
                    shuriken.setFill(Color.valueOf("d2ff26"));
                }

            }));
            collisionMan.setCycleCount(Timeline.INDEFINITE);
            collisionMan.play();

    }

    public void initialize_game() {

        hero = new Player();
        hero.addToScene(game_screen);
        initialize_islands();
        initialize_orcs();
        initialize_clouds();
        game_objects.add(hero);
    }

    // generate islands and chests and TNTs
    private void initialize_islands() {
        for(int i = 0; i < 50; i++) {
            islands.add(new Island(new Position(0)));
            int x = 114 + i*300 + r1.nextInt(10);
            int y = 400 + r1.nextInt(100);
            islands.get(i).addToScene(game_screen, x, y);
            game_objects.add(islands.get(i));

            int generate_chest = r1.nextInt(10);
            if(generate_chest == 0 || i == 1) {
                // generate chest at this island
//                Chest temp = new CoinChest();
                System.out.println("THIS IS WORKING12");
                chests.add(new CoinChest());
                System.out.println("THIS IS WORKING");
                chests.get(chests.size()-1).addToScene(game_screen, x, y, islands.get(i));
                game_objects.add(chests.get(chests.size()-1));
            }

            int generate_tnt = r1.nextInt(7);
            if(generate_tnt == 0 || i == 2) {
                tnts.add(new TNT(this));
                tnts.get(tnts.size()-1).addToScene(game_screen, x, y, islands.get(i));
                game_objects.add(tnts.get(tnts.size()-1));
            }
        }
    }

    private void initialize_orcs() {
        int red_or_green;
        for(int i = 0; i < 50; i++) {
            red_or_green = r1.nextInt(2);
            if(red_or_green == 0) {
                // red
                orcs.add(new RedOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*250 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }else {
                // green
                orcs.add(new GreenOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*240 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }
        }

    }

    private void initialize_clouds() {
        for(int i = 0; i < 50; i++) {
            int no_of_cloud = r1.nextInt(2);
            for(int j = 0; j < 2; j++) {
                if(no_of_cloud == 0 && j == 1) {
                    continue;
                }
                ImageView image_fx = add_cloud();
                image_fx.setPreserveRatio(true);
                image_fx.setFitWidth(200 + r1.nextInt(150));
                image_fx.setLayoutX(220 + i*700 + r1.nextInt(100));
                image_fx.setViewOrder(20000);
                if(j == 0) {
                    image_fx.setLayoutY(100 + r1.nextInt(50));
                }else {
                    image_fx.setLayoutY(400 + r1.nextInt(50));
                }
                game_screen.getChildren().add(image_fx);
                TranslateTransition cloud_mover = new TranslateTransition(Duration.seconds(300), image_fx);
                cloud_mover.setByX(-20000);
                cloud_mover.setCycleCount(1);
                cloud_mover.play();

            }
        }
    }

    private ImageView add_cloud() {
        System.out.println("CREATING Cloud");
        
        int orc_num = 1 + r1.nextInt(8);
        Image image = new Image("com/ap43iiitd/willhero/imageres/Sprites/Clouds/C (".concat(String.valueOf(orc_num)).concat(").png"));
        return new ImageView(image);
    }

//    public void initialize_chests() {
//
//    }

    public void update() {

    }

    public ArrayList<GameObject> getGame_objects() {
        return game_objects;
    }

    public Player getHero() {
        return hero;
    }


    private void resolveCollision() {}
    public void menuHandler() {}
    private void populateScreen() {}
    private void saveGame() {}
    private void serialize() {}
    private void deserialize() {}
    private void loadGame() {}
    private void pause() {}
    private void resume() {}
    private void gameOver() {}
    private void respawn() {}
    public void addToRender() {}
    public void eraseObject() {}

    // TEMPORARY FUNCTION FOR IN_SCENE


    public void setIn_scene(ArrayList<GameObject> in_scene) {
        this.in_scene = in_scene;
    }

    public ArrayList<GameObject> getIn_scene() {
        return in_scene;
    }
}


