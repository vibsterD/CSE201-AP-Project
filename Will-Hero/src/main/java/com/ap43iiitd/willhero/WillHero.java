package com.ap43iiitd.willhero;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class WillHero extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WillHero.class.getResource("EnterMenuOverlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("WillHerooooo!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Boolean newGame = false;
        if(false){
            MainGamePage mpg = fxmlLoader.getController();
            Timeline tl = mpg.collisionMan;
            ImageView pauseSense = mpg.pauseButton;
            pauseSense.setOnMouseClicked(e->{
                System.out.println("DON'T TOUCH THAT");
                mpg.paused = true;
                tl.pause();
            });
        }

    }

    public static void main(String[] args) {
        launch();
    }
}