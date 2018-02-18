package com.speculate.objects;

public class Loan {

    private int index;
    private int amount;

    public Loan(int index, int amount) {
        this.index = index;
        this.amount = amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
