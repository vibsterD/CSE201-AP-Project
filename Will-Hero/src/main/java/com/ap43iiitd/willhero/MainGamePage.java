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

        Orc orc2 = new GreenOrc(null);
        orc2.addToScene(GameScreenMove);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            orc2.update();
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setRate(30);
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
            System.out.println(e.getStackTrace());
        }
    }



}