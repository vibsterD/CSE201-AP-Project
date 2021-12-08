package com.ap43iiitd.willhero;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainGamePage {
    @FXML
    private Label tapToPlay;

    @FXML
    private Button tapSense;

    @FXML
    private ImageView Orc;

    @FXML
    private Group GameScreenMove;

    @FXML
    private ImageView Hero;

    public void initialize(){
        TranslateTransition heroTranslation = new TranslateTransition(Duration.millis(500), Hero);
        heroTranslation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        heroTranslation.setByY(-100);
        heroTranslation.setAutoReverse(true);
        heroTranslation.setCycleCount(1000);
        heroTranslation.play();
        TranslateTransition translation = new TranslateTransition(Duration.millis(400), Orc);
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByY(-60);
        translation.setAutoReverse(true);
        translation.setCycleCount(1000);
        translation.play();
    }

    @FXML
    protected void onHelloButtonClick() {
        tapToPlay.setText("For the next deadline!");
        TranslateTransition translation = new TranslateTransition(Duration.millis(150), GameScreenMove);
        translation.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .7, .7));
        translation.setByX(-150);
        translation.setCycleCount(1);
        translation.play();
    }


}