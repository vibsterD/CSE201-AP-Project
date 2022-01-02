package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameWinOverlay {

    @FXML
    private StackPane winText;

    @FXML
    public void initialize(){
        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), winText);
        fadeIn.setToValue(1);
        fadeIn.setOnFinished(e->{
            FadeTransition outYouGo = new FadeTransition(Duration.millis(1000), winText);
            outYouGo.setToValue(0);
            outYouGo.setOnFinished(f->{
                sceneSwitcher("EnterMenuOverlay.fxml");
            });
            outYouGo.play();
        });
        fadeIn.play();
    }

    private void sceneSwitcher(String fxmlName){
        Stage runningInstance = (Stage) winText.getScene().getWindow();
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
}
