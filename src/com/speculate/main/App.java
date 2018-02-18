package com.speculate.main;

import com.speculate.controller.MainUIController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class App {

    private Timeline gameloop;
    private MainUIController mainUIController;

    public App(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public void start() {

        gameloop = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            update();
        }));
        gameloop.setCycleCount(Animation.INDEFINITE);
        gameloop.play();
    }

    private void update(){
        mainUIController.update();
    }

}
