package com.example.mwm.view;

import com.example.mwm.network.Connection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField textFieldUsername, textFieldPassword;

    @FXML
    private void login() {
        if (textFieldUsername.getText()!=null && textFieldPassword.getText()!=null)
            Connection.login(textFieldUsername.getText(), textFieldPassword.getText());
    }

    @FXML
    private void register() {
        if (textFieldUsername.getText()!=null && textFieldPassword.getText()!=null)
            Connection.register(textFieldUsername.getText(), textFieldPassword.getText());
    }

}
