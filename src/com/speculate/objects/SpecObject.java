package com.speculate.objects;

import com.speculate.controller.BuyAmountController;
import com.speculate.controller.MainUIController;
import com.speculate.player.Player;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private SimpleIntegerProperty availability = new SimpleIntegerProperty();
    private int index;
    private Player player;
    private MainUIController mainUIController;
    private Button btn_buy;

    public SpecObject(int index, String name, double value, double growth, int availability, Player player, MainUIController mainUIController) {
        this.index = index;
        this.name = name;
        this.valueProp = new SimpleDoubleProperty(value);
        this.growthProp = new SimpleDoubleProperty(growth);
        initialValue = value;
        this.availability.set(availability);
        this.player = player;
        this.mainUIController = mainUIController;
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


        Label labelAvailable = new Label();
        labelAvailable.textProperty().bind(Bindings.convert(availability.asString()));
        labelDesign(labelAvailable);

        btn_buy = new Button("kaufen");
        btn_buy.setOnAction(event -> {
            BuyAmountController buyAmountController = new BuyAmountController(availability.get());
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

            buyAmountController.btn_okay.setOnAction(event1 -> {
                //System.out.println(buyAmountController.combo_box.getSelectionModel().getSelectedItem());
                mainUIController.vbox_player.getChildren().add(player.buy(this, (int)buyAmountController.combo_box.getSelectionModel().getSelectedItem()));
                checkForAvailability();
                dialogStage.close();
            });

            buyAmountController.btn_abort.setOnAction(event1 -> {
                dialogStage.close();
            });

            dialogStage.showAndWait();

            //player.buy()
        });

        hbox.getChildren().addAll(labelName, labelValue, labelGrowth, labelDifference, labelAvailable, btn_buy);

        return hbox;
    }

    private void checkForAvailability(){
        if(availability.get()  == 0){
            btn_buy.setDisable(true);
        } else btn_buy.setDisable(false);
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
        return availability.get();
    }

    public void setAvailability(int availability) {
        this.availability.set(availability);
    }

    public int getIndex() {
        return index;
    }

}
