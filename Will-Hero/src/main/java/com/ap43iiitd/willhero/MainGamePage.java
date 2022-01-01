package com.ap43iiitd.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainGamePage {
    final static Random r1 = new Random(1337);

    @FXML
    private Rectangle pauseScreenFilter;

    @FXML
    protected ImageView pauseButton;

    protected Boolean paused = false;

    @FXML
    private Label coins;

    @FXML
    private StackPane gamePane;

    @FXML
    private AnchorPane game_screen;

    @FXML
    private Label score;

    @FXML
    private Rectangle sword;

    @FXML
    private Rectangle shuriken;

    private Player hero;

    @FXML
    private Group playPrompt;

    Timeline collisionMan;

    Game game;

    Boolean counter_active = false;
    Boolean dash_flag = false;
    Boolean dash_translation = false;

    private ArrayList<GameObject> game_objects;
    private ArrayList<Game> games;

    public void initialize() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Stark.txt"));
            game = (Game) in.readObject();
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }

//        game = new Game(game_screen, sword, shuriken, gamePane, coins);
        game_objects = game.getGame_objects();
        hero = game.getHero();

        game.play();

    }


    @FXML
    protected void onHelloButtonClick() {
        //TODO: When collide with treasure chest and got weapon, add opacity
        game.heroDash(score);
    }

    @FXML
    protected void pauseButtonAction() {
        game.pause(pauseButton, pauseScreenFilter);
    }

    @FXML
    protected void onSwordClick(){
        System.out.println("Clicked sword");
        hero.getHelmet().setCurrent_weapon(0);
    }

    @FXML
    protected void onShurikenClick(){
        System.out.println("Shuricken clicked");
        hero.getHelmet().setCurrent_weapon(1);
    }

}