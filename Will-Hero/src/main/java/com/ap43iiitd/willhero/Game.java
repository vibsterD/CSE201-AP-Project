package com.ap43iiitd.willhero;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    Player hero;

    public Game(AnchorPane game_screen) {
        this.game_screen = game_screen;
        game_objects = new ArrayList<GameObject>();
        islands = new ArrayList<Island>();
        orcs = new ArrayList<Orc>();
        initialize_game();
    }


    public void initialize_game() {

        hero = new Player();
        hero.addToScene(game_screen);
        initialize_islands();
        initialize_orcs();
        initialize_clouds();
        game_objects.add(hero);
    }

    private void initialize_islands() {
        for(int i = 0; i < 50; i++) {
            islands.add(new Island(new Position(0)));
            islands.get(i).addToScene(game_screen, 114 + i*300, 400 + r1.nextInt(100));
            game_objects.add(islands.get(i));
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
    private void play() {}
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
}


