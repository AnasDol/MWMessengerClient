package com.example.mwm.network;

import com.example.mwm.MessengerApplication;
import com.example.mwm.model.Chat;
import com.example.mwm.view.MainController;
import com.example.mwm.view.SelectController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Connection {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3443;

    private static Socket clientSocket;
    private static Scanner inMessage;
    private static PrintWriter outMessage;

    public static String username;

    private static Thread thread;

    public Connection() {

        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
            System.out.println("клиент: подключение установлено");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("не удалось подключиться");
        }

        thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (!Thread.interrupted()) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = inMessage.nextLine();
                            System.out.println(inMes);

                            if (inMes.equalsIgnoreCase("#register#ok")) {
                               MessengerApplication.openView("main-view.fxml");
                            }

                            else if (inMes.equalsIgnoreCase("#register#failed")) {
                                MessengerApplication.alarm("Имя уже используется");
                            }

                            else if (inMes.equalsIgnoreCase("#login#ok")) {
                                MessengerApplication.openView("main-view.fxml");
                            }

                            else if (inMes.equalsIgnoreCase("#login#failed")) {
                                MessengerApplication.alarm("Неверное имя пользователя или пароль");
                            }

                            else if (inMes.equalsIgnoreCase("#join#chat#ok")) {
                                MessengerApplication.openView("main-view.fxml");
                            }

                            else if (inMes.equalsIgnoreCase("#join#chat#failed")) {
                                MessengerApplication.alarm("Неверный пароль");
                            }

                            else if (inMes.equalsIgnoreCase("#user#chats")) {

                                ArrayList<String> chats_info = new ArrayList<>();

                                // получаем текстовую инфу о чатах
                                String response = waitForMessage();
                                while (!response.equalsIgnoreCase("#user#chats#end")) {
                                    chats_info.add(response);
                                    response = waitForMessage();
                                }

                                ArrayList<Chat> chats = new ArrayList<>();

                                // парсим и создаем лист объектов
                                for (String info : chats_info) {
                                    chats.add(new Chat(info));
                                }

                                MainController.updateChatList(chats);

                            }

                            else if (inMes.equalsIgnoreCase("#global#chats")) {

                                ArrayList<String> chats_info = new ArrayList<>();

                                // получаем текстовую инфу о чатах
                                String response = waitForMessage();
                                while (!response.equalsIgnoreCase("#global#chats#end")) {
                                    chats_info.add(response);
                                    response = waitForMessage();
                                }

                                ArrayList<Chat> chats = new ArrayList<>();

                                // парсим и создаем лист объектов
                                for (String info : chats_info) {
                                    chats.add(new Chat(info));
                                }

                                SelectController.updateChatList(chats);

                            }

                            else if (inMes.equalsIgnoreCase("#messages")) {

                                ArrayList<String> messages_info = new ArrayList<>();

                                // получаем текстовую инфу о сообщениях
                                String response = waitForMessage();
                                while (!response.equalsIgnoreCase("#messages#end")) {
                                    messages_info.add(response);
                                    response = waitForMessage();
                                }

                                ArrayList<Chat.Message> messages = new ArrayList<>();

                                // парсим и создаем лист объектов
                                for (String info : messages_info) {
                                    messages.add(new Chat.Message(info));
                                }

                                MainController.updateMessages(messages);

                            }

                            else if (inMes.equalsIgnoreCase("#new#chat#ok")) {
                                MessengerApplication.openView("main-view.fxml");
                            }

                            else if (inMes.equalsIgnoreCase("#new#chat#failed")) {
                                MessengerApplication.alarm("Чат с таким названием уже существует");
                            }

                            else if (inMes.equalsIgnoreCase("#update")) {
                                String name = waitForMessage();
                                if (MainController.chosenChat.name.equalsIgnoreCase(name)) {
                                    requestMessages(name);
                                }
                            }

                        }
                    }
                } catch (Exception e) {e.printStackTrace();}
            }
        });

        thread.setDaemon(true);
        thread.start();


    }


    public static void sendMessage(String msg) {
        outMessage.println(msg);
        outMessage.flush();
        System.out.println("client: sending: "+msg);
    }

    public static String waitForMessage() {
        String clientMsg = "";
        while(true){
            if (inMessage.hasNext()) {
                clientMsg = inMessage.nextLine();
                break;
            }
        }
        System.out.println(clientMsg);
        return clientMsg;
    }

    public static void login(String username, String password) {

        sendMessage("#login");
        sendMessage(username);
        sendMessage(password);
        Connection.username = username;

    }

    public static void register(String username, String password) {

        sendMessage("#register");
        sendMessage(username);
        sendMessage(password);
        Connection.username = username;

    }

    public static void close() {
        sendMessage("#connection#close");
    }

    public static void requestUserChats() {
        sendMessage("#user#chats#request");
    }

    public static void requestGlobalChats() {
        sendMessage("#global#chats#request");
    }

    public static void joinChat(String name, String password) {
        sendMessage("#join#chat");
        sendMessage(name);
        sendMessage(password);
    }

    public static void requestMessages(String name) {
        sendMessage("#messages#request");
        sendMessage(name);
    }

    public static void sendUserMessage(String chatname, String messageText) {
        sendMessage("#message");
        sendMessage(chatname);
        sendMessage(messageText);
    }

    public static void requestNewChat(String name, String password) {
        sendMessage("#new#chat");
        sendMessage(name);
        sendMessage(password);
    }

}