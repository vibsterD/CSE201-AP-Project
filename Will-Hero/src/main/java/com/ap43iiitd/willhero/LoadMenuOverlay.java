package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadMenuOverlay {

    @FXML
    private Group clouds;

    @FXML
    private AnchorPane main_screen;

    @FXML
    private Group load_game_boxes;


    private FadeTransition playFadeTransition(){
        FadeTransition fTrans = new FadeTransition();
        fTrans.setDuration(Duration.millis(500));
        fTrans.setToValue(0);
        fTrans.setNode(main_screen);
        fTrans.play();
        return fTrans;
    }

    public void switchToEnterMenu() {
        playFadeTransition().setOnFinished(e->sceneSwitcher("EnterMenuOverlay.fxml"));
    }


    public void initialize() {
        TranslateTransition clouds_transition = new TranslateTransition(Duration.minutes(2), clouds);
        clouds_transition.setByX(-2000);
        clouds_transition.setCycleCount(Timeline.INDEFINITE);
        clouds_transition.play();
    }

    private void sceneSwitcher(String fxmlName){
        Stage runningInstance = (Stage) clouds.getScene().getWindow();
        System.out.println(runningInstance);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlName));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            runningInstance.setScene(scene);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
        }

    }

}
