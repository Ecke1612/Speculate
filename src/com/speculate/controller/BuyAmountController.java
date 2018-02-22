package com.speculate.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class BuyAmountController {

    @FXML
    VBox vbox_main;
    @FXML
    public ComboBox combo_box;
    @FXML
    public Button btn_okay;
    @FXML
    public Button btn_abort;

    private int availability;

    public BuyAmountController(int availability) {
        this.availability = availability;
    }

    public void initialize() {
        for(int i = 1; i < availability-1; i++) {
            combo_box.getItems().add(i);
        }
        combo_box.getSelectionModel().select(0);
    }

}
