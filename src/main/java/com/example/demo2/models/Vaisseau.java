package com.example.demo2.models;

import com.example.demo2.Game;
import com.example.demo2.controller.MissileThread;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


public class Vaisseau  extends Rectangle {

    private int newX = 0;

    public Vaisseau(double width, double x, double y){
        super(x, y, width, width);
        Image dots = new Image(String.valueOf(Game.class.getResource("img/unnamed.png")));
        this.setFill(new ImagePattern(dots, x - width, y - width, width, width, false));
    }

    public void moveLeft(){
        if (newX + this.getX() > 0) {
            newX -= 20;
            this.setTranslateX(newX);
        }
    }

    public void moveRight(){
        if (newX + this.getWidth() + this.getX() < 800) {
            newX+= 20;
            this.setTranslateX(newX);
        }
    }

    public void tirer(Group group, ArrayList<Cube3D> cubes, Game game){
        double y = this.getY();
        double x = this.getX() + this.getTranslateX() + (this.getWidth() / 2) - 3;
        Rectangle missile = new Rectangle(x, y, 5, 20);
        missile.setArcWidth(20d);
        missile.setArcHeight(20d);
        missile.setFill(Color.WHITE);
        group.getChildren().add(missile);
        new MissileThread(missile,group, cubes, game).start();
    }
}
