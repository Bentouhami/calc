package com.javafx.calc.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.javafx.calc.mvc.Controller.Controller;

/**
 * JavaFX App
 */
public class App extends Application {

    private Controller controller;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        controller = new Controller();
        stage.setMinWidth(200);
        stage.setMaxWidth(500);
        stage.setMinHeight(300);
        stage.setMaxHeight(500);
        scene = new Scene(controller.getRoot(), 300, 300);

        stage.setTitle("My Fx Calculatrice");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}