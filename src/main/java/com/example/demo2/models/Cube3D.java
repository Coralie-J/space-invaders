package com.example.demo2.models;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;


public class Cube3D extends Box {

    protected Color couleur;

    public Cube3D(int w, int h, int d, int x, int y, Color color){
        super(w,h,d);
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(0);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        this.getTransforms().addAll(rxBox, ryBox, rzBox);
        this.setTranslateX(x);
        this.setTranslateY(y);

        this.couleur = color;
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        this.setMaterial(material);


        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(this);
        translateTransition.setDuration(Duration.millis(4000));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.setByX(100d);
        translateTransition.play();

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(this);
        transition1.setDuration(Duration.millis(120000));
        transition1.setCycleCount(1);
        transition1.setByY(400d);
        transition1.play();

        transition1.setOnFinished(actionEvent -> {
            // System.out.println(actionEvent.getSource().getClass());
            /*if (game.getCubes().size() > 0){
                Platform.runLater(() -> {
                    stage.hide();
                    game.getStage().setUserData(new AtomicBoolean(false));
                    try {
                        new FinPartie().start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }*/

        });


        RotateTransition transition = new RotateTransition();
        transition.setNode(this);
        transition.setDuration(Duration.millis(9000));
        transition.setCycleCount(RotateTransition.INDEFINITE);
        transition.setAxis(Rotate.Y_AXIS);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setByAngle(360);
        transition.play();

    }

    public Color getCouleur() {
        return couleur;
    }
}
