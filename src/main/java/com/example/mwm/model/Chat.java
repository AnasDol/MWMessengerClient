package com.example.mwm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Chat implements Serializable {

    public String name;

    public String type;

    public String history;

    public String lastMessage;

    public ImageView image;

    public ArrayList<Message> messages;


    public Chat(String info) { // example: "#name#public#lastMessage"

        String[] info_arr = info.split("#");

        this.name = info_arr[1];
        this.type = info_arr[2];
        this.lastMessage = info_arr[3];

        image = new ImageView();
        try {
            image.setImage(new Image(new FileInputStream("src/main/resources/com/example/mwm/icons/chat_icon.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static class Message {

        public String senderName;

        public String dateTime;

        public String text;

        public Message(String message_info) {

            String[] arr = message_info.split("#");

            this.senderName = arr[1];
            this.dateTime = arr[2];
            this.text = arr[3];

        }

    }



}
