package com.ap43iiitd.willhero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainGamePage {
    @FXML
    private Label tapToPlay;

    @FXML
    protected void onHelloButtonClick() {
        tapToPlay.setText("For the next deadline!");
    }
}