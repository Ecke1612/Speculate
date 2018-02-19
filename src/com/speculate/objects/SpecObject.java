package com.speculate.objects;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class SpecObject {

    private String name;
    private SimpleDoubleProperty valueProp;
    private SimpleDoubleProperty growthProp;
    private double initialValue;
    private SimpleDoubleProperty differenceProp = new SimpleDoubleProperty();
    private int availability;
    private int index;

    public SpecObject(int index, String name, double value, double growth, int availability) {
        this.index = index;
        this.name = name;
        this.valueProp = new SimpleDoubleProperty(value);
        this.growthProp = new SimpleDoubleProperty(growth);
        initialValue = value;
        this.availability = availability;
    }

    public HBox draw() {
        HBox hbox = new HBox(10);

        Label labelName = new Label(name);
        labelDesign(labelName);

        Label labelValue = new Label();
        labelValue.textProperty().bind(Bindings.convert(valueProp.asString("%.2f")));
        labelDesign(labelValue);

        Label labelGrowth = new Label();
        labelGrowth.textProperty().bind(Bindings.convert(growthProp.asString("%.2f")));
       labelDesign(labelGrowth);

        Label labelDifference = new Label();
        labelDifference.textProperty().bind(Bindings.convert(differenceProp.asString("%.2f")));
        labelDesign(labelDifference);

        hbox.getChildren().addAll(labelName, labelValue, labelGrowth, labelDifference);

        return hbox;
    }

    private void labelDesign(Label label) {
        label.setPadding(new Insets(2));
        label.setStyle("" +
                "-fx-font-size: 16px;" +
                "-fx-fill: #818181;");
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return valueProp.get();
    }

    public void setValue(double value) {
        valueProp.set(value);
    }

    public double getGrowth() {
        return growthProp.get();
    }

    public void setGrowth(double growth) {
        growthProp.set(growth);
    }

    public double getDifferenceProp() {
        return differenceProp.get();
    }

    public void setDifferenceProp(double value){
        differenceProp.set(value);
    }

    public double getInitialValue() {
        return initialValue;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getIndex() {
        return index;
    }
}
