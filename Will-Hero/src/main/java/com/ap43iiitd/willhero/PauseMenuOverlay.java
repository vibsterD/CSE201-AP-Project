package com.ap43iiitd.willhero;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PauseMenuOverlay {

    @FXML
    protected StackPane overlayPane;

    @FXML
    protected Button resumeButton;

    @FXML
    protected Button saveButton;

    @FXML
    protected VBox saveGroup;

    private Game instanceOfGame;

    public void setInstanceOfGame(Game instanceOfGame) {
        this.instanceOfGame = instanceOfGame;
    }

    int saveNo;

    private void save(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("saves/"+String.valueOf(saveNo)));
            out.writeObject(instanceOfGame);
            saveGroup.setDisable(true);
            saveGroup.setOpacity(0);
        }
        catch (Exception e){
            System.out.println("Couldn't save it...");

        }
    }

    @FXML
    void save1(){
        saveNo = 1;
        save();
    }

    @FXML
    void save2(){
        saveNo = 2;
        save();
    }

    @FXML
    void save3(){
        saveNo = 3;
        save();
    }

    @FXML
    void save4(){
        saveNo = 4;
        save();
    }

    @FXML
    void resumeGame() {
        //this method is just for clearing stuff
        //will not be linked with resumeButton
        System.out.println("Attempting resume");
        FadeTransition fTrans = new FadeTransition(Duration.millis(300), overlayPane);
        fTrans.setToValue(0);
        fTrans.play();
        overlayPane.setDisable(true);

    }

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
