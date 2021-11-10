package ru.croc.task11;

import java.util.Date;

public class Lot{
    private volatile int currentCost;
    private volatile String userName;
    private Date endOfBids;

    public Lot(int currentCost, String userName, Date endOfBids) {
        this.currentCost = currentCost;
        this.userName = userName;
        this.endOfBids = endOfBids;
    }

    public synchronized void makeBid(int currentCost, String userName) {
        if (currentCost > this.currentCost && new Date().before(this.endOfBids)) {
            this.currentCost = currentCost;
            this.userName = userName;
        } else {
            System.out.println("Your bid is less than existing one or the auction is end.");
        }
    }

    public synchronized String getWinner() {
        return this.userName + " is winner of auction!";
    }
}
