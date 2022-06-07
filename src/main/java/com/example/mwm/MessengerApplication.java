package com.example.mwm;

import com.example.mwm.network.Connection;
import com.example.mwm.view.MainController;
import com.example.mwm.view.SelectController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class MessengerApplication extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        Connection connection = new Connection();
        MessengerApplication.stage = stage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        openView("register-view.fxml");
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openView(String viewToOpen) {
        //stage.hide();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (viewToOpen.equalsIgnoreCase("register-view.fxml")) {
                    try {
                        openRegisterView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (viewToOpen.equalsIgnoreCase("main-view.fxml")) {
                    try {
                        openMainView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (viewToOpen.equalsIgnoreCase("select-view.fxml")) {
                    try {
                        openSelectView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (viewToOpen.equalsIgnoreCase("input-view.fxml")) {
                    try {
                        openInputView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (viewToOpen.equalsIgnoreCase("create-view.fxml")) {
                    try {
                        openCreateView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    private static void openRegisterView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessengerApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();
        newStage.setTitle("Hello!");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        newStage.show();
        stage = newStage;
    }

    private static void openMainView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Scene scene = null;

        loader.setLocation(MessengerApplication.class.getResource("main-view.fxml"));
        root = loader.load();
        scene = new Scene(root);

        Stage newStage = new Stage();

        newStage.setTitle("Enjoy communication!");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        newStage.show();
        stage = newStage;


        /*stage.setScene(scene);
        stage.show();



        FXMLLoader fxmlLoader = new FXMLLoader(MessengerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();

        newStage.setTitle("Enjoy communication!");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        newStage.show();
        stage = newStage;*/
    }

    private static void openSelectView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Scene scene = null;

        loader.setLocation(MessengerApplication.class.getResource("select-view.fxml"));
        root = loader.load();
        scene = new Scene(root);

        Stage newStage = new Stage();

        newStage.setTitle("Select chat");
        newStage.setScene(scene);
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                openView("main-view.fxml");
            }
        });
        stage.hide();
        newStage.show();
        stage = newStage;

    }

    private static void openInputView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Scene scene = null;

        loader.setLocation(MessengerApplication.class.getResource("input-view.fxml"));
        root = loader.load();
        scene = new Scene(root);

        Stage newStage = new Stage();

        newStage.setTitle("Input password");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                MessengerApplication.openView("select-view.fxml");
            }
        });
        newStage.show();
        stage = newStage;


        /*stage.setScene(scene);
        stage.show();



        FXMLLoader fxmlLoader = new FXMLLoader(MessengerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();

        newStage.setTitle("Enjoy communication!");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        newStage.show();
        stage = newStage;*/
    }

    private static void openCreateView() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        Scene scene = null;

        loader.setLocation(MessengerApplication.class.getResource("create-view.fxml"));
        root = loader.load();
        scene = new Scene(root);

        Stage newStage = new Stage();

        newStage.setTitle("Create new chat");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                MessengerApplication.openView("select-view.fxml");
            }
        });
        newStage.show();
        stage = newStage;


        /*stage.setScene(scene);
        stage.show();



        FXMLLoader fxmlLoader = new FXMLLoader(MessengerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage newStage = new Stage();

        newStage.setTitle("Enjoy communication!");
        newStage.setScene(scene);
        stage.hide();
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Connection.close();
            }
        });
        newStage.show();
        stage = newStage;*/
    }


    public static void alarm(String message) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Внимание:");
                alert.setContentText(message);
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.CANCEL) {
                    System.out.println("cansel pressed");
                }
            }
        });


    }








}