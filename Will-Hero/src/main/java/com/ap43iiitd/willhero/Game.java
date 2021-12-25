package com.ap43iiitd.willhero;

import javafx.scene.layout.AnchorPane;

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
        game_objects.add(hero);
    }

    public void initialize_islands() {
        for(int i = 0; i < 50; i++) {
            islands.add(new Island(1, new Position(0)));
            islands.get(i).addToScene(game_screen, 114 + i*300, 400 + r1.nextInt(100));
            game_objects.add(islands.get(i));
        }
    }

    public void initialize_orcs() {
        int red_or_green;
        for(int i = 0; i < 70; i++) {
            red_or_green = r1.nextInt(2);
            if(red_or_green == 0) {
                // red
                orcs.add(new RedOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*200 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }else {
                // green
                orcs.add(new GreenOrc(new Position()));
                orcs.get(i).addToScene(game_screen, 220 + i*200 + r1.nextInt(20), 300 + r1.nextInt(10));
                game_objects.add(orcs.get(i));
            }
        }

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


