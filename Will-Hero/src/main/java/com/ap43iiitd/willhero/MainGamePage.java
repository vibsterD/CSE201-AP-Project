package com.ap43iiitd.willhero;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Random;

public class MainGamePage {
    @FXML
    private Label tapToPlay;

    @FXML
    private AnchorPane tapSense;

    @FXML
    private ImageView Orc;

    @FXML
    private AnchorPane GameScreenMove;

    @FXML
    private ImageView Hero;

    @FXML
    private Label score;

    @FXML
    private ImageView pauseButton;

    final static Random r1 = new Random(1337);

    Timeline timeline;

    public void initialize(){

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
            heroTranslationDown.interpolatorProperty().set(Interpolator.SPLINE(1,1,.7,.7));
            heroTranslationDown.setByY(100);
            TranslateTransition orcTranslationUp = new TranslateTransition(Duration.millis(400), Orc);
            orcTranslationUp.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .9, .9));
            int diff = r1.nextInt(70);
            orcTranslationUp.setByY(-70+ diff);
            TranslateTransition orcTranslationDown = new TranslateTransition(Duration.millis(400), Orc);
            orcTranslationDown.interpolatorProperty().set(Interpolator.SPLINE(1,1,.7,.7));
            orcTranslationDown.setByY(70-diff);
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
        tapToPlay.setText("For the next deadline!");
        score.setText(String.valueOf(Integer.parseInt(score.getText())+1));
        TranslateTransition translation = new TranslateTransition(Duration.millis(150), GameScreenMove);
        ///// DO NOT MOVE BY GAME SCREEN, MOVE EACH OBJECT
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByX(-150);
        translation.setCycleCount(1);
        translation.play();
    }

    @FXML
    protected void pauseClick() {
        System.out.println("Hello World");
        timeline.pause();
    }


}