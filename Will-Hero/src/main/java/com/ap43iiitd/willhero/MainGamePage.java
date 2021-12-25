package com.ap43iiitd.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class MainGamePage {
    final static Random r1 = new Random(1337);

    @FXML
    private Rectangle pauseScreenFilter;

    @FXML
    protected ImageView pauseButton;

    protected Timeline timeline;
    protected Boolean paused = false;

    @FXML
    private Label tapToPlay;

    @FXML
    private AnchorPane tapSense;


    @FXML
    private StackPane gamePane;

    @FXML
    private AnchorPane GameScreenMove;

    @FXML
    private Label score;

    private Player hero;
    private Island island;
    private Orc orc2;

    Boolean counter_active = false;
    Boolean dash_flag = false;

    private ArrayList<GameObject> game_objects;
//    private ArrayList<Island> islands;

    public void initialize() {
        game_objects = new ArrayList<GameObject>();


        for(int i = 0; i < 50; i++) {
            game_objects.add(new Island(1, new Position(0)));
            ((Island)game_objects.get(i)).addToScene(GameScreenMove, 114 + i*300, 400 + r1.nextInt(100));
        }

        island = (Island) game_objects.get(0);

        orc2 = new GreenOrc(new Position());
        orc2.addToScene(GameScreenMove);
        hero = new Player();
        hero.addToScene(GameScreenMove);

        game_objects.add(hero);
        game_objects.add(orc2);

//        island = new Island(1, new Position());
//        island.addToScene(GameScreenMove);

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
//            orc2.getPosition().updatePosition(orc2.getImage_fx());
//            hero.getPosition().updatePosition(hero.getImage_fx());
//            System.out.println("Sanity check");

        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(250);
        timeline.play();

        Timeline collisionMan = new Timeline(new KeyFrame(Duration.millis(5), event->{
//            hero.collide(orc2);
//            hero.collide(island);
//            orc2.collide(island);
            ArrayList <GameObject> in_scene = new ArrayList<GameObject>();
            double x_pos = hero.getImage_fx().getTranslateX();
            for (GameObject game_object : game_objects) {
                if (Math.abs(game_object.getImage_fx().getBoundsInParent().getCenterX() - x_pos) < 1280) {
                    // in scene
                    in_scene.add(game_object);
                }
            }
//            System.out.println("inscene: " + in_scene.size());
            for(int i = 0; i < in_scene.size(); i++) {
                in_scene.get(i).getPosition().updatePosition(in_scene.get(i).getImage_fx());
                for(int j = i + 1; j < in_scene.size(); j++) {

                    in_scene.get(i).collide(in_scene.get(j));
                    in_scene.get(j).collide(in_scene.get(i));
                }
            }

        }));
        collisionMan.setCycleCount(Timeline.INDEFINITE);
        collisionMan.play();
    }

    @FXML
    protected void onHelloButtonClick() {
        //also spawn a new island with an orc maybe
        if (paused) return;
        tapToPlay.setText("For the next deadline!");
        score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
        TranslateTransition translation = new TranslateTransition(Duration.millis(150), GameScreenMove);
        ///// DO NOT MOVE BY GAME SCREEN, MOVE EACH OBJECT
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByX(-150);
//        Hero.setTranslateX(Hero.getTranslateX() + 150);
        hero.getPosition().setVelocity(150, 0);
        TranslateTransition translation2 = new TranslateTransition(Duration.millis(150), hero.getImage_fx());
        ///// DO NOT MOVE BY GAME SCREEN, MOVE EACH OBJECT
        translation2.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation2.setByX(150);
        translation.setCycleCount(1);
        translation.play();
        translation2.setCycleCount(1);
        translation2.play();
//        hero.getPosition().updatePosition(hero.getHero_fx());
        translation2.setOnFinished(ev -> {
            System.out.println("Vel-y during dash: "+ hero.getPosition().getVel_y());
            hero.getPosition().setVelocity(1, hero.getPosition().getVel_y());
        });
    }

    @FXML
    protected void pauseButtonAction() {
        paused = true;
        timeline.pause();
        FadeTransition fTrans = new FadeTransition(Duration.millis(300), pauseScreenFilter);
        fTrans.setToValue(1);
        fTrans.play();
        pauseButton.setDisable(true);

        //TODO: Implement menu methods in PauseMenuOverlay Class

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PauseMenuOverlay.fxml"));
        try {
            fxmlLoader.load();
            PauseMenuOverlay pmo= fxmlLoader.getController();
            System.out.println(pmo.overlayPane.getChildren());
            gamePane.getChildren().add(pmo.overlayPane);
            pmo.resumeButton.setOnAction(e->{
                System.out.println("TAKING ACTION");
                pmo.resumeGame();
                System.out.println("OPACITY 0");
                timeline.play();
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



}