package com.example.mwm.view;

import com.example.mwm.MessengerApplication;
import com.example.mwm.network.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InputController {

    @FXML
    private TextField textFieldPassword;

    @FXML
    private void close(ActionEvent actionEvent) {
        MessengerApplication.openView("select-view.fxml");
    }

    @FXML
    private void input(ActionEvent actionEvent) {
        if (!textFieldPassword.getText().equalsIgnoreCase(""))
            Connection.joinChat(SelectController.chosenChat.name, textFieldPassword.getText());
    }
}
