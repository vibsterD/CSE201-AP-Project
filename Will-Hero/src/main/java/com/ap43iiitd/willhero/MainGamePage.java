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
    private ImageView Orc;

    @FXML
    private StackPane gamePane;

    @FXML
    private AnchorPane GameScreenMove;

    @FXML
    private ImageView Hero;

    @FXML
    private Label score;

    public void initialize() {

        //have a static array of initialized objects
        //read from that array and if orc or hero, animate jump
        //create translateTransition without node and duration
        //nodes can be iterated, duration specified
        //getCurrent Y, jump delta of 10?
        //getCurrent Y, jump with delta (720-current) and if collide with land, destroy animation
        //specify interpolator curve with a quadratic equation.

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {

            TranslateTransition heroTranslationUp = new TranslateTransition(Duration.millis(400), Hero);
            heroTranslationUp.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .9, .9));
            heroTranslationUp.setByY(-100);
            TranslateTransition heroTranslationDown = new TranslateTransition(Duration.millis(400), Hero);
            heroTranslationDown.interpolatorProperty().set(Interpolator.SPLINE(1, 1, .7, .7));
            heroTranslationDown.setByY(100);
            TranslateTransition orcTranslationUp = new TranslateTransition(Duration.millis(400), Orc);
            orcTranslationUp.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .9, .9));
            int diff = r1.nextInt(70);
            orcTranslationUp.setByY(-70 + diff);
            TranslateTransition orcTranslationDown = new TranslateTransition(Duration.millis(400), Orc);
            orcTranslationDown.interpolatorProperty().set(Interpolator.SPLINE(1, 1, .7, .7));
            orcTranslationDown.setByY(70 - diff);
//            Transition squish = new Transition() {
//                {
//                    setCycleDuration(Duration.millis(100));
//                }
//
//                protected void interpolate(double frac) {
//                    System.out.println(frac);
//                    Hero.setFitHeight(Hero.getFitHeight()*frac);
////                    Hero.setY(Hero.getY()-Hero.getFitHeight()+0.5);
//
//                }
//
//            };
//            squish.setCycleCount(1);
//            squish.setAutoReverse(true);
            SequentialTransition st1 = new SequentialTransition(heroTranslationUp, heroTranslationDown);
            SequentialTransition st2 = new SequentialTransition(orcTranslationUp, orcTranslationDown);
            st1.play();
            st2.play();

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
        translation.setCycleCount(1);
        translation.play();
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
        }
    }



}