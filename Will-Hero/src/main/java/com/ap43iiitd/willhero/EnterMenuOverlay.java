package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class EnterMenuOverlay {

        @FXML
        private StackPane mainScreen;

        @FXML
        private Group cloudMover;

        @FXML
        private Group logoWillHero;

        @FXML
        private Button newGame;

        @FXML
        private ImageView tapFinger;

        @FXML
        private Button loadGame;

        @FXML
        public void makeNewGame() {
                playFadeTransition().setOnFinished(e->sceneSwitcher("MainGamePage.fxml", true));
        }

        private FadeTransition playFadeTransition(){
                FadeTransition fTrans = new FadeTransition();
                fTrans.setDuration(Duration.millis(500));
                fTrans.setToValue(0);
                fTrans.setNode(mainScreen);
                fTrans.play();
                return fTrans;
        }

        public void initialize(){
                TranslateTransition t1 = new TranslateTransition(Duration.millis(1000), logoWillHero);
                t1.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t1.setByY(20);
                t1.setAutoReverse(true);
                t1.setCycleCount(Timeline.INDEFINITE);
                t1.play();

                TranslateTransition t2 = new TranslateTransition(Duration.minutes(2), cloudMover);
                t2.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t2.setByX(-2000);
//                t2.setAutoReverse(true);
                t2.setCycleCount(Timeline.INDEFINITE);
                t2.play();

                TranslateTransition t3 = new TranslateTransition(Duration.millis(300), tapFinger);
                t3.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
                t3.setByY(10);
                t3.setAutoReverse(true);
                t3.setCycleCount(Timeline.INDEFINITE);
                t3.play();

        }

        @FXML
        public void loadGame(){
                playFadeTransition().setOnFinished(e->sceneSwitcher("LoadMenuOverlay.fxml", false));
        }

        private void sceneSwitcher(String fxmlName, Boolean isGame){
                Stage runningInstance = (Stage) newGame.getScene().getWindow();
                System.out.println(runningInstance);
                FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource(fxmlName));
                try {
                        Parent loader = fxmlLoader.load();
                        Scene scene = new Scene(loader, 1280, 720);
                        if(isGame){
                                MainGamePage mainGamePage = fxmlLoader.getController();
                                mainGamePage.initializeGame();
                        }
                        runningInstance.setScene(scene);
                }
                catch (Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("In-short: Looks like you misplaced some files");
                }

        }

}

