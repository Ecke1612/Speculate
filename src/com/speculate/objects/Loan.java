package com.speculate.objects;

import javafx.beans.property.SimpleIntegerProperty;

public class Loan {

    private int indexOfSo;
    private SimpleIntegerProperty amount = new SimpleIntegerProperty();

    public Loan(int indexOfSo, int amount) {
        this.indexOfSo = indexOfSo;
        this.amount.set(amount);
    }

    public int getIndexOfSo() {
        return indexOfSo;
    }

    public void setIndexOfSo(int indexOfSo) {
        this.indexOfSo = indexOfSo;
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }
}
