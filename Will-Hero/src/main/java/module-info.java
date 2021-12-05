module com.ap43iiitd.willhero {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ap43iiitd.willhero to javafx.fxml;
    exports com.ap43iiitd.willhero;
}