package com.speculate.objects;

public class Loan {

    private int indexOfSo;
    private int amount;

    public Loan(int indexOfSo, int amount) {
        this.indexOfSo = indexOfSo;
        this.amount = amount;
    }

    public int getIndexOfSo() {
        return indexOfSo;
    }

    public void setIndexOfSo(int indexOfSo) {
        this.indexOfSo = indexOfSo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
