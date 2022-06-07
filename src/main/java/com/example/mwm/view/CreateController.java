package com.example.mwm.view;

import com.example.mwm.MessengerApplication;
import com.example.mwm.model.Chat;
import com.example.mwm.network.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateController {

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private CheckBox checkBoxIsPrivate;
    @FXML
    private Text textPassword;

    public void initialize() {
        textFieldPassword.visibleProperty().bindBidirectional(checkBoxIsPrivate.selectedProperty());
        textPassword.visibleProperty().bindBidirectional(checkBoxIsPrivate.selectedProperty());
    }

    public void createChat(ActionEvent actionEvent) {

        if (!textFieldName.getText().equalsIgnoreCase("")) {

            if (checkBoxIsPrivate.isSelected()) // приватный чат
            {
                if (!textFieldPassword.getText().equalsIgnoreCase(""))
                    Connection.requestNewChat(textFieldName.getText(), textFieldPassword.getText());
            }
            else // публичный чат
            {
                Connection.requestNewChat(textFieldName.getText(), "$null");
            }
        }

    }

    public void back() {
        MessengerApplication.openView("select-view.fxml");
    }
}
