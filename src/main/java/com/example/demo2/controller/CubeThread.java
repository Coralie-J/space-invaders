package com.example.demo2.controller;

import com.example.demo2.Game;
import com.example.demo2.models.Cube3D;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class CubeThread implements Runnable {

    private ArrayList<Cube3D> cubes;
    private Game game;
    private Group group;

    public CubeThread(Game game, ArrayList<Cube3D> cubes, Group group) {
        this.cubes = cubes;
        this.game = game;
        this.group = group;
    }

    @Override
    public void run() {
        double y = 30d;
        AtomicReference<Double> translation_y = new AtomicReference<>((double) 100);

        while (translation_y.get() < 640) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                for (Node node : group.getChildren()) {
                    if (node.getClass() == Cube3D.class) {
                        translation_y.set(node.getTranslateY() + y);
                        node.setTranslateY(translation_y.get());
                    }
                }
            });
        }

        boolean joueur_gagne = this.cubes.isEmpty();

        Platform.runLater(() -> {
            try {
                this.game.finDePartie(joueur_gagne);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
