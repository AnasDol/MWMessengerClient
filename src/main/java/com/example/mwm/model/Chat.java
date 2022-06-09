package com.example.mwm.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Chat {

    public String name;

    public String type;

    public String lastMessage;

    public ImageView image;


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

        public Color color;

        public String text;

        public Message(String message_info) {

            String[] arr = message_info.split("#");

            this.senderName = arr[1];

            String[] rgb = arr[2].split("/");
            color = new Color(Double.parseDouble(rgb[0])/1000, Double.parseDouble(rgb[1])/1000, Double.parseDouble(rgb[2])/1000, 0.6).brighter().brighter().brighter();
            this.dateTime = arr[3];
            this.text = arr[4];

        }

    }



}
