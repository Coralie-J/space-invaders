package com.example.demo2;

import com.example.demo2.models.Cube3D;
import com.example.demo2.models.Vaisseau;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Game extends Application {

    private Vaisseau vaisseau;
    private Group group;
    private Label score_affiche;
    private ArrayList<Cube3D> cubes;
    private int score;
    private Stage stage;
    private static final Color[] colors = new Color[]{Color.RED, Color.BLUE, Color.MEDIUMSPRINGGREEN , Color.SALMON, Color.VIOLET};

    @Override
    public void start(Stage stage) {

        this.vaisseau = new Vaisseau(70, 350d, 730d);
        this.score = 0;
        this.stage = stage;
        this.score_affiche = new Label("Score : 0");
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

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public Stage getStage(){
        return this.stage;
    }

    public ArrayList<Cube3D> getCubes() {
        return cubes;
    }

    public void augmenteScore(Cube3D cube3D){
        if (Color.RED.equals(cube3D.getCouleur()))
            score += 50;
        else if (Color.BLUE.equals(cube3D.getCouleur()))
            score += 40;
        else if (Color.MEDIUMSPRINGGREEN.equals(cube3D.getCouleur()))
            score += 30;
        else if (Color.SALMON.equals(cube3D.getCouleur()))
            score += 20;
        else
            score +=10;
        this.score_affiche.setText("Score : " + score);
    }
}
