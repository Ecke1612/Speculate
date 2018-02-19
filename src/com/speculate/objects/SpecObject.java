package com.speculate.objects;

import com.speculate.controller.BuyAmountController;
import com.speculate.player.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SpecObject {

    private String name;
    private SimpleDoubleProperty valueProp;
    private SimpleDoubleProperty growthProp;
    private double initialValue;
    private SimpleDoubleProperty differenceProp = new SimpleDoubleProperty();
    private int availability;
    private int index;
    private Player player;
    //private Button btn_buy = new Button("kaufen");

    public SpecObject(int index, String name, double value, double growth, int availability, Player player) {
        this.index = index;
        this.name = name;
        this.valueProp = new SimpleDoubleProperty(value);
        this.growthProp = new SimpleDoubleProperty(growth);
        initialValue = value;
        this.availability = availability;
        this.player = player;
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

        Button btn_buy = new Button("kaufen");
        btn_buy.setOnAction(event -> {
            BuyAmountController buyAmountController = new BuyAmountController();
            Stage dialogStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Alert_Buy_Amount.fxml"));
            fxmlLoader.setController(buyAmountController);
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialogStage.setTitle("Menge");
            dialogStage.setScene(new Scene(root));
            dialogStage.showAndWait();
            player.buy()
        });

        hbox.getChildren().addAll(labelName, labelValue, labelGrowth, labelDifference, btn_buy);

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

    public SimpleDoubleProperty valuePropProperty() {
        return valueProp;
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

    public Button getBtn_buy() {
        return btn_buy;
    }
}
