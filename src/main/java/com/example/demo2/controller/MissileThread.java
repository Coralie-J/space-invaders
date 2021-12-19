package com.example.demo2.controller;

import com.example.demo2.FinPartie;
import com.example.demo2.Game;
import com.example.demo2.models.Cube3D;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MissileThread extends Thread {

    private Rectangle missile;
    private Group group;
    private ArrayList<Cube3D> cubes;
    private Game game;

    public MissileThread(Rectangle m, Group group, ArrayList<Cube3D> cubes, Game game){
        this.cubes = cubes;
        this.missile = m;
        this.game = game;
        this.group = group;
    }

    @Override
    public void run() {
        AtomicBoolean collision = new AtomicBoolean(false);
        for (int i=0; i > -800d && ! (collision.get()); i-=10){

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int finalI = i;

            Platform.runLater(() -> {
                missile.setTranslateY(finalI);

                for (Node node: group.getChildren()) {
                    if (node.getClass() == Cube3D.class) {
                        if (missile.getBoundsInParent().intersects(node.getBoundsInParent())) {
                            game.augmenteScore((Cube3D) node);
                            cubes.remove(node);
                            group.getChildren().remove(node);
                            group.getChildren().remove(missile);
                            collision.set(true);
                            break;
                        }
                    }
                }

                if (cubes.size() == 0){
                        try {
                            this.game.getStage().hide();
                            new FinPartie().start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });
        }
    }
}
