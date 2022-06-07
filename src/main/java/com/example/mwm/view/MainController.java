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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class MainController {

    @FXML
    private ScrollPane chatScrollPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField textFieldMessage;
    @FXML
    private ScrollPane messageScrollPane;


    private static ListView<ChatBox> chatBoxListView = new ListView<>();
    private static ListView<MessageBox> messageBoxListView = new ListView<>();

    //private static VBox vBox;

    public static Chat chosenChat = null;

    public static void updateChatList(ArrayList<Chat> chats) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatBoxListView.getItems().clear();
                for (Chat chat:chats) {
                    chatBoxListView.getItems().add(new ChatBox(chat));
                }
            }
        });

    }

    public static void updateMessages(ArrayList<Chat.Message> messages) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageBoxListView.getItems().clear();
                for (Chat.Message message : messages) {
                    messageBoxListView.getItems().add(new MessageBox(message));
                }
            }
        });


    }

    @FXML
    public void initialize() {
        chatBoxListView.setPrefHeight(chatScrollPane.getPrefHeight());
        chatScrollPane.setContent(chatBoxListView);
        Connection.requestUserChats();

        messageScrollPane.vvalueProperty().bind(messageBoxListView.heightProperty());
        messageScrollPane.setContent(messageBoxListView);
        messageBoxListView.setPrefHeight(668);
        messageBoxListView.setPrefWidth(736);



        // устанавливаем слушатель для отслеживания выбора
        MultipleSelectionModel<ChatBox> selectionModel = chatBoxListView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<ChatBox>(){
            @Override
            public void changed(ObservableValue<? extends ChatBox> observableValue, ChatBox oldValue, ChatBox newValue) {
                if (newValue!=null) {
                    chosenChat = newValue.getChat();
                    Connection.requestMessages(chosenChat.name);
                }
                else chosenChat = null;

            }
        });
    }


    public void selectNewChat(ActionEvent actionEvent) {

        MessengerApplication.openView("select-view.fxml");
    }

    @FXML
    private void sendMessage(ActionEvent actionEvent) {
        if (textFieldMessage.getText()!=null && chosenChat!=null)
            Connection.sendUserMessage(chosenChat.name, textFieldMessage.getText());
    }
}
