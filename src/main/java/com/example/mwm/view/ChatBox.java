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
import java.util.Objects;

public class ChatBox extends HBox {

    @FXML
    private Text name;
    @FXML
    private Text lastMessage;
    @FXML
    private ImageView image;

    private Chat chat;

    public ChatBox(Chat chat) {
        this.chat = chat;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(MessengerApplication.class.getResource("chat-box.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name.setText(chat.name);
        if (Objects.equals(chat.type, "public")) {
            lastMessage.setText("Публичный чат");
        }
        else lastMessage.setText("Приватный чат");

        try {
            image.setImage(new Image(new FileInputStream("src/main/resources/com/example/mwm/icons/chat_icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        image.setFitWidth(32);
        image.setFitHeight(32);
    }

    public Chat getChat() {
        return chat;
    }
}
