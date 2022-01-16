package com.example.demo2;

import com.example.demo2.thread.CubeThread;
import com.example.demo2.models.Cube3D;
import com.example.demo2.models.Vaisseau;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;


public class Game extends Application {

    private Vaisseau vaisseau;
    private Group group;
    private Label score_affiche;
    private ArrayList<Cube3D> cubes;
    private int score;
    private Stage stage;
    private static final Color[] colors = new Color[]{Color.RED, Color.CYAN, Color.MEDIUMSPRINGGREEN , Color.YELLOW, Color.VIOLET};

    @Override
    public void start(Stage stage) {

        this.vaisseau = new Vaisseau(70, 350d, 730d);
        this.score = 0;
        this.stage = stage;
        this.score_affiche = new Label("Score : 0");
        this.score_affiche.setFont(new Font(20));
        this.score_affiche.setTextFill(Color.WHITE);
        this.group = new Group(vaisseau, score_affiche);
        this.cubes = new ArrayList<>();

        int c = 0;
        for (int j = 15; j < 375; j += 75) {
            Color color = colors[c];
            for (int i = 20; i < 720; i += 80) {
                Cube3D cube = new Cube3D(35, 35, 35, 10 + i, 40 + j, color);
                this.group.getChildren().add(cube);
                this.cubes.add(cube);
            }
            c++;
        }

        Scene scene = new Scene(group, 800, 800);
        scene.setFill(Color.BLACK);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getText().equals("q") || keyEvent.getCode() == KeyCode.LEFT)
                    vaisseau.moveLeft();
                else if (keyEvent.getText().equals("d") || keyEvent.getCode() == KeyCode.RIGHT)
                    vaisseau.moveRight();
                else if (keyEvent.getText().equals("z") || keyEvent.getCode() == KeyCode.UP)
                    vaisseau.tirer(Game.this.group, Game.this.cubes, Game.this);
            }
        });

        stage.setTitle("Space invaders");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        new Thread(new CubeThread(this, cubes, group)).start();
    }

    public void augmenteScore(Cube3D cube3D){
        if (Game.colors[0].equals(cube3D.getCouleur()))
            score += 100;
        else if (Game.colors[1].equals(cube3D.getCouleur()))
            score += 70;
        else if (Game.colors[2].equals(cube3D.getCouleur()))
            score += 50;
        else if (Game.colors[3].equals(cube3D.getCouleur()))
            score += 20;
        else
            score +=10;
        this.score_affiche.setText("Score : " + score);
    }

    public void finDePartie(boolean gagne) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("fin-partie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 750);

        Button button_exit = (Button) fxmlLoader.getNamespace().get("exit_button");
        Button button_restart = (Button) fxmlLoader.getNamespace().get("restart_button");
        Label info = (Label) fxmlLoader.getNamespace().get("message");
        Label score = (Label) fxmlLoader.getNamespace().get("score");
        score.setText(this.score_affiche.getText());

        if (gagne) {
            info.setText("Bravo !");
            info.setTextFill(Color.MEDIUMSPRINGGREEN);
        }
        else {
            info.setText("Game over");
            info.setTextFill(Color.RED);
        }

        ScaleTransition animation = new ScaleTransition(Duration.millis(2000), info);
        animation.setByX(4f);
        animation.setByY(4f);
        animation.setCycleCount(1);
        animation.play();

        button_exit.setOnAction(actionEvent -> System.exit(0));
        button_restart.setOnAction(actionEvent -> new Game().start(new Stage()));

        stage.setTitle("Space invaders");
        stage.setScene(scene);
        stage.show();
    }
}
