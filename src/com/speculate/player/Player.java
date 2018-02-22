package com.speculate.player;

import com.speculate.objects.Loan;
import com.speculate.objects.SpecObject;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Player {

    private SimpleDoubleProperty money = new SimpleDoubleProperty();
    private ArrayList<Loan> possession = new ArrayList<>();
    private double fortune = 0;

    public Player(double money, VBox vboxMain) {
        this.money.set(money);
    }

    public HBox init() {
        HBox hbox0 = new HBox(10);

        Label textMoney = new Label("Geld: ");

        Label labelmoney = new Label();
        labelmoney.textProperty().bind(Bindings.convert(money.asString("%.2f")));

        Label textFortune = new Label("Gesamt Vermögen: ");

        hbox0.getChildren().addAll(textMoney, labelmoney, textFortune);
        return hbox0;
    }

    public HBox buy(SpecObject so, int amount) {

        if(amount <= so.getAvailability()) {
            if(!checkPoessetion(so, amount)) {
                possession.add(new Loan(so.getIndex(), amount));
            }
            so.setAvailability(so.getAvailability() - amount);

            HBox hbox = new HBox(10);

            Label labelName = new Label(so.getName());
            labelDesign(labelName);

            Label labelValue = new Label(Double.toString(so.getValue()));
            labelValue.textProperty().bind(Bindings.convert(so.valuePropProperty().asString("%.2f")));
            labelDesign(labelValue);

            Label labelAmountText = new Label("Anzahl: ");
            labelDesign(labelAmountText);

            Label labelAmount =  new Label();
            labelAmount.textProperty().bind(Bindings.convert(possession.get(possession.size()-1).amountProperty().asString()));
            labelDesign(labelAmount);

            hbox.getChildren().addAll(labelName, labelValue, labelAmountText, labelAmount);

            System.out.println("es wurden " + amount + " Anleihen von " + so.getName() + " gekauft");

            return hbox;
        } else {
            System.out.println("soviele kannst du nicht kaufen");
            return null;
        }
    }

    private boolean checkPoessetion(SpecObject so, int amount){
        boolean hit = false;
        for(Loan l : possession) {
            if(so.getIndex() == l.getIndexOfSo()){
                l.setAmount(l.getAmount() + amount);
                hit = true;
            }
        }
        return hit;
    }

    public double getMoney() {
        return money.get();
    }

    public SimpleDoubleProperty moneyProperty() {
        return money;
    }

    public void setMoney(double money) {
        this.money.set(money);
    }

    public ArrayList<Loan> getPosetion() {
        return possession;
    }

    public void addLoan(int index, int amount) {
        possession.add(new Loan(index, amount));
    }

    public void removeLoan(int index, int amount) {
        int counter = 0;
        for(Loan l : possession){
            if(index == l.getIndexOfSo()){
                if(amount < l.getAmount()){
                    l.setAmount(l.getAmount() - amount);
                } else if(amount == l.getAmount()){
                    possession.remove(counter);
                } else {
                    System.out.println("das ist mehr als du hast");
                }
            }
            counter++;
        }
    }

    private void labelDesign(Label label) {
        label.setPadding(new Insets(2));
        label.setStyle("" +
                "-fx-font-size: 16px;" +
                "-fx-fill: #818181;");
    }
}
