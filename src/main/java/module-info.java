module com.example.mwm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mwm to javafx.fxml;
    exports com.example.mwm;
    exports com.example.mwm.view;
    opens com.example.mwm.view to javafx.fxml;
    exports com.example.mwm.network;
    opens com.example.mwm.network to javafx.fxml;
}