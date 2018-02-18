package com.speculate.player;

import com.speculate.objects.Loan;

import java.util.ArrayList;

public class Player {

    private double money;
    private ArrayList<Loan> possession = new ArrayList<>();
    private double fortune = 0;

    public Player(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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
            if(index == l.getIndex()){
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
}
