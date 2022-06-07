package com.example.mwm.view;

import com.example.mwm.MessengerApplication;
import com.example.mwm.model.Chat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MessageBox extends HBox {

    @FXML
    private Text textSenderName;
    @FXML
    private Text textDatetime;
    @FXML
    private Text textMessage;

    private Chat.Message message;

    public MessageBox(Chat.Message message) {
        this.message = message;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MessengerApplication.class.getResource("message-box.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        textSenderName.setText(message.senderName);

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMM:yyyy H:m");
        textDatetime.setText(message.dateTime.toString());

        textMessage.setText(message.text);

    }
}
