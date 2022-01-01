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
    private Label tapToPlay;

    @FXML
    private AnchorPane tapSense;


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

    public void initialize() {

        game = new Game(game_screen, sword, shuriken);
        game_objects = game.getGame_objects();
        hero = game.getHero();

        game.play();

    }


    @FXML
    protected void onHelloButtonClick() {
        //TODO: When collide with treasure chest and got weapon, add opacity
        //also spawn a new island with an orc maybe
        if(!hero.getAlive()) {
            return;
        }
        if (paused || hero.getPosition().getVel_x()>0) return;

//        dash_translation = true;
        tapToPlay.setText("For the next deadline!");
        score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
//        TranslateTransition translation = new TranslateTransition(Duration.millis(150), game_screen);
//        /// DO NOT MOVE BY GAME SCREEN, MOVE EACH OBJECT
//        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
//        translation.setByX(-150);
////        Hero.setTranslateX(Hero.getTranslateX() + 150);
////        hero.getPosition().setVelocity(150, 0);
////        TranslateTransition translation2 = new TranslateTransition(Duration.millis(150), hero.getImage_fx());
////        ///// DO NOT MOVE BY GAME SCREEN, MOVE EACH OBJECT
////        translation2.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
////        translation2.setByX(150);
//        translation.setCycleCount(1);
////        hero.getPosition().setDash_flag(true);
//        translation.play();
//        translation2.setCycleCount(1);
//        translation2.play();
//        hero.getPosition().updatePosition(hero.getHero_fx());
//        translation2.setOnFinished(ev -> {
////            System.out.println("Vel-y during dash: "+ hero.getPosition().getVel_y());
////            hero.getPosition().setVelocity(0, hero.getPosition().getVel_y());
//            hero.getPosition().setDash_flag(false);
//            dash_translation = false;
//        });
        Position heroPos = hero.getPosition();
//        System.out.println("gamescreenpos: " +game_screen.getTranslateX());
        heroPos.setVelocity(39.9, heroPos.getVel_y());

        Weapon att = hero.getHelmet().getCurrent_weapon();
        if(att!=null) {
            att.attack(game_objects, game_screen, hero.getImage_fx().getTranslateX()+hero.getImage_fx().getLayoutX(), hero.getImage_fx().getTranslateY()+hero.getImage_fx().getLayoutY());
        }

        //TODO: Add screen normalisation logic


    }

    @FXML
    protected void pauseButtonAction() {
        paused = true;
        collisionMan.pause();
        FadeTransition fTrans = new FadeTransition(Duration.millis(300), pauseScreenFilter);
        fTrans.setToValue(1);
        fTrans.play();
        pauseButton.setDisable(true);

        //TODO: Implement menu methods in PauseMenuOverlay Class

        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("PauseMenuOverlay.fxml"));
        try {
            fxmlLoader.load();
            PauseMenuOverlay pmo= fxmlLoader.getController();
            System.out.println(pmo.overlayPane.getChildren());
            gamePane.getChildren().add(pmo.overlayPane);
            pmo.resumeButton.setOnAction(e->{
                System.out.println("TAKING ACTION");
                pmo.resumeGame();
                System.out.println("OPACITY 0");
                collisionMan.play();
                pauseScreenFilter.setOpacity(0);
                pauseButton.setDisable(false);
                paused=false;

            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
            System.out.println(e.getStackTrace());
        }
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

    private void gameOver(){
        collisionMan.pause();
        if(hero.hasRespawned()) {
            //game is really over
        }
        //add a button to tapsense

        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("GameOverOverlay.fxml"));
        try {
            fxmlLoader.load();
            GameOverOverlay goc = fxmlLoader.getController();
            gamePane.getChildren().add(goc.overlayPane);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
            System.out.println(e.getStackTrace());
        }

        System.out.println("Testing deez");


        hero.setRespawned();

    }


}