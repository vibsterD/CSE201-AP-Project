package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameOverOverlay {

    @FXML
    Label cost;

    @FXML
    HBox mainMenuClick;

    @FXML
    Button reviveButton;

    @FXML
    AnchorPane overlayPane;

    @FXML
    ProgressBar progressBar;

    @FXML
    void mainMenu(){
        //les go main menu
        sceneSwitcher("EnterMenuOverlay.fxml");
    }

    private void sceneSwitcher(String fxmlName){
        Stage runningInstance = (Stage) overlayPane.getScene().getWindow();
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
