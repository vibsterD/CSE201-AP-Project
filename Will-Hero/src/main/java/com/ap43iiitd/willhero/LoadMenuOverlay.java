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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadMenuOverlay {

    @FXML
    private Group clouds;

    @FXML
    private AnchorPane main_screen;

    private FadeTransition playFadeTransition(double set_to_value){
        FadeTransition fTrans = new FadeTransition();
        fTrans.setDuration(Duration.millis(500));
        fTrans.setToValue(set_to_value);
        fTrans.setNode(main_screen);
        fTrans.play();
        return fTrans;
    }

    public void switchToEnterMenu() {
        playFadeTransition(0).setOnFinished(e->sceneSwitcher("EnterMenuOverlay.fxml"));
    }


    public void initialize() {
        TranslateTransition clouds_transition = new TranslateTransition(Duration.minutes(2), clouds);
        clouds_transition.interpolatorProperty().set(Interpolator.SPLINE(.1, .1, .2, .2));
        clouds_transition.setByX(-2000);
        clouds_transition.setCycleCount(Timeline.INDEFINITE);
        clouds_transition.play();
    }

    private void sceneSwitcher(String fxmlName){
        Stage runningInstance = (Stage) clouds.getScene().getWindow();
        System.out.println(runningInstance);
        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource(fxmlName));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            runningInstance.setScene(scene);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("In-short: Looks like you misplaced some files");
        }

    }

    private void saveFileSwitcher(String saveFile){
        Stage runningInstance = (Stage) clouds.getScene().getWindow();
        System.out.println(runningInstance);
        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("MainGamePage.fxml"));
        try {
            Parent loader = fxmlLoader.load();
            Scene scene = new Scene(loader, 1280, 720);
            MainGamePage mainGamePage = fxmlLoader.getController();
            if( mainGamePage.loadGame(saveFile)) {
                runningInstance.setScene(scene);
            }else {
                playFadeTransition(1);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("misplaced files");
        }

    }

    @FXML
    public void loadSave1(){
        playFadeTransition(0).setOnFinished(event -> saveFileSwitcher("saves/1"));
    }

    @FXML
    public void loadSave2(){
        playFadeTransition(0).setOnFinished(event -> saveFileSwitcher("saves/2"));
    }

    @FXML
    public void loadSave3(){
        playFadeTransition(0).setOnFinished(event -> saveFileSwitcher("saves/3"));
    }

    @FXML
    public void loadSave4(){
        playFadeTransition(0).setOnFinished(event -> saveFileSwitcher("saves/4"));
    }
}
