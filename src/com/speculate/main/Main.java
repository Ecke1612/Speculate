package com.speculate.main;

import com.speculate.controller.MainUIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainUIController mainUIController = new MainUIController();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainUI.fxml"));
        fxmlLoader.setController(mainUIController);
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Speculate");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        App app = new App(mainUIController);
        app.start();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
