package com.example.mwm.view;

import com.example.mwm.MessengerApplication;
import com.example.mwm.model.Chat;
import com.example.mwm.network.Connection;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Objects;

public class SelectController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchTextField;

    private static ArrayList<ChatBox> data = new ArrayList<>();
    private static ObservableList<ChatBox> observableList = FXCollections.observableList(data);
    private static ListView<ChatBox> listView;
    private static SortedList<ChatBox> sortedList;
    private static FilteredList<ChatBox> filteredList;

    public static Chat chosenChat = null;

    public static void updateChatList(ArrayList<Chat> chats) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                data.clear();
                for (Chat chat:chats) {
                    data.add(new ChatBox(chat));
                }
            }
        });

    }

    public void initialize() {

        listView = new ListView<>();
        scrollPane.setContent(listView);
        listView.setPrefHeight(400);
        listView.setPrefWidth(471);
        Connection.requestGlobalChats();

        // устанавливаем слушатель для отслеживания выбора
        MultipleSelectionModel<ChatBox> selectionModel = listView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<ChatBox>(){
            @Override
            public void changed(ObservableValue<? extends ChatBox> observableValue, ChatBox oldValue, ChatBox newValue) {
                chosenChat = newValue.getChat();
            }
        });


        filteredList = new FilteredList<>(observableList);


        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(chatBox -> {
                // If filter text is empty, display all persons.
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if(chatBox.getChat().name.toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches first name
                }

                return false; //Does not match
            });
        });

       sortedList = new SortedList<>(filteredList);

       listView.setItems(sortedList);

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

    public void search(ActionEvent actionEvent) {
    }
}
