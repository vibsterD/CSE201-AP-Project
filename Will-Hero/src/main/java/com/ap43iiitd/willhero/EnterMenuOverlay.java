package com.ap43iiitd.willhero;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


public class EnterMenuOverlay {

        @FXML
        private Group cloudMover;

        @FXML
        private Group logoWillHero;

        @FXML
        private ImageView newGame;

        @FXML
        private ImageView tapFinger;

        @FXML
        void makeNewGame(MouseEvent event) {
                System.out.println("MAKING");
        }

        public void initialize(){
                TranslateTransition t1 = new TranslateTransition(Duration.millis(1000), logoWillHero);
                t1.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t1.setByY(20);
                t1.setAutoReverse(true);
                t1.setCycleCount(Timeline.INDEFINITE);
                t1.play();

                TranslateTransition t2 = new TranslateTransition(Duration.minutes(1), cloudMover);
                t2.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t2.setByX(-2000);
                t2.setCycleCount(Timeline.INDEFINITE);
                t2.play();

                TranslateTransition t3 = new TranslateTransition(Duration.millis(300), tapFinger);
                t3.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t3.setByY(10);
                t3.setAutoReverse(true);
                t3.setCycleCount(Timeline.INDEFINITE);
                t3.play();

        }

}

