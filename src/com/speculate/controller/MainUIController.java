package com.speculate.controller;

import com.speculate.objects.SpecObject;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class MainUIController {

    @FXML
    public VBox mainCenterVBox;

    private ArrayList<SpecObject> specObjects = new ArrayList<>();

    public MainUIController() {

    }

    public void initialize() {
        createSpecObjects();
    }

    private void createSpecObjects() {
        mainCenterVBox.getChildren().clear();

        specObjects.add(new SpecObject("Facebook", 500, 1.8));
        specObjects.add(new SpecObject("Apple", 1200, 7.5));
        specObjects.add(new SpecObject("Google", 3200, -9.5));
        specObjects.add(new SpecObject("Microsoft", 800, -3.2));

        for(SpecObject so : specObjects){
            mainCenterVBox.getChildren().add(so.draw());
        }
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
