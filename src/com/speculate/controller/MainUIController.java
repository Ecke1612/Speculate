package com.speculate.controller;

import com.speculate.objects.SpecObject;
import com.speculate.player.Player;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class MainUIController {

    @FXML
    private VBox mainCenterVBox;
    @FXML
    VBox vbox_player;


    private ArrayList<SpecObject> specObjects = new ArrayList<>();
    private Player player;

    public MainUIController() {
        player = new Player(100, vbox_player);
    }

    public void initialize() {
        createSpecObjects();
        vbox_player.getChildren().add(player.init());
    }

    private void createSpecObjects() {
        mainCenterVBox.getChildren().clear();

        specObjects.add(new SpecObject(0, "Facebook", 500, 1.8, 10));
        specObjects.add(new SpecObject(1, "Apple", 1200, 7.5, 5));
        specObjects.add(new SpecObject(2, "Google", 3200, -9.5, 12));
        specObjects.add(new SpecObject(3, "Microsoft", 800, -3.2, 3));

        for(SpecObject so : specObjects){
            mainCenterVBox.getChildren().add(so.draw());
        }
        player.buy(specObjects.get(0));
    }

    public ArrayList<SpecObject> getSpecObjects() {
        return specObjects;
    }

    public void update() {
        for(SpecObject so : specObjects) {
            Random r = new Random();
            so.setValue(so.getValue() + ((so.getGrowth() / so.getValue()) * 100));
            so.setGrowth((r.nextGaussian()*2*(r.nextDouble() * (so.getGrowth() / 2))+1) + so.getGrowth());
            so.setDifferenceProp(so.getValue() - so.getInitialValue());
        }
    }

}
