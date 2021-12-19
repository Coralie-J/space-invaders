package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FinPartie extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(FinPartie.class.getResource("fin-partie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Button button_exit = (Button) fxmlLoader.getNamespace().get("exit_button");
        Button button_restart = (Button) fxmlLoader.getNamespace().get("restart_button");

        button_exit.setOnAction(actionEvent -> {
            System.exit(0);
        });

        button_restart.setOnAction(actionEvent -> {
            stage.hide();
            new Game().start(new Stage());
        });

        stage.setTitle("Space invaders");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
