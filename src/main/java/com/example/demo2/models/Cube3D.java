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
    private TranslateTransition translation_x;

    public Cube3D(int w, int h, int d, int x, int y, Color color){
        super(w,h,d);
        this.couleur = color;

        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(0);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        this.getTransforms().addAll(rxBox, ryBox, rzBox);
        this.setTranslateX(x);
        this.setTranslateY(y);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        this.setMaterial(material);

        this.translation_x = new TranslateTransition(Duration.millis(2000), this);
        this.translation_x.setCycleCount(TranslateTransition.INDEFINITE);
        this.translation_x.setAutoReverse(true);
        this.translation_x.setByX(100d);
        this.translation_x.play();

        RotateTransition transition = new RotateTransition(Duration.millis(9000), this);
        transition.setCycleCount(RotateTransition.INDEFINITE);
        transition.setAxis(Rotate.Y_AXIS);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.setByAngle(360);
        transition.play();

    }

    public TranslateTransition getTranslation_x() {
        return translation_x;
    }

    public Color getCouleur() {
        return couleur;
    }
}
