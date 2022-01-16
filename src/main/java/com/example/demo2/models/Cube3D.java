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
        this.couleur = color;
        this.setRotate(50d);

        this.setTranslateX(x);
        this.setTranslateY(y);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        this.setMaterial(material);

        TranslateTransition translation_x = new TranslateTransition(Duration.millis(2000), this);
        translation_x.setCycleCount(TranslateTransition.INDEFINITE);
        translation_x.setAutoReverse(true);
        translation_x.setByX(100d);
        translation_x.play();

        RotateTransition transition = new RotateTransition(Duration.millis(9000), this);
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
