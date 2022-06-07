package com.example.mwm.view;

import com.example.mwm.MessengerApplication;
import com.example.mwm.model.Chat;
import com.example.mwm.network.Connection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.ScrollPane;

import java.util.ArrayList;
import java.util.Objects;

public class SelectController {

    @FXML
    private ScrollPane scrollPane;

    private static ListView<ChatBox> listView;

    public static Chat chosenChat = null;

    public static void updateChatList(ArrayList<Chat> chats) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                listView.getItems().clear();
                for (Chat chat:chats) {
                    listView.getItems().add(new ChatBox(chat));
                }
            }
        });

    }

    public void initialize() {

        listView = new ListView<>();
        scrollPane.setContent(listView);
        Connection.requestGlobalChats();
        listView.getItems().add(new ChatBox(new Chat("#Девачки#public#Люся: пока")));

        // устанавливаем слушатель для отслеживания выбора
        MultipleSelectionModel<ChatBox> selectionModel = listView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<ChatBox>(){
            @Override
            public void changed(ObservableValue<? extends ChatBox> observableValue, ChatBox oldValue, ChatBox newValue) {
                chosenChat = newValue.getChat();
            }
        });

    }

    @FXML
    private void joinChat(ActionEvent actionEvent) {

        if (!Objects.equals(chosenChat.type, "public")) // если чат приватный, требуем пароль
        {
            MessengerApplication.openView("input-view.fxml");
        }
        else {
            Connection.joinChat(chosenChat.name, "$null");
        }

    }

    @FXML
    private void createChat(ActionEvent actionEvent) {
        MessengerApplication.openView("create-view.fxml");
    }
}
