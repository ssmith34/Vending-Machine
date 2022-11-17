package com.techelevator.models;

public abstract class Items {

    private String name;
    private double price;
    private String slotNumber;
    private int amountLeft;
    private String dispenseMessage;

    public Items(String name, double price, String slotNumber, String dispenseMessage) {
        this.name = name;
        this.price = price;
        this.slotNumber = slotNumber;
        amountLeft = 6;
        this.dispenseMessage = dispenseMessage;

    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getSlotNumber() {
        return this.slotNumber;
    }

    public int getAmountLeft() {
        return this.amountLeft;
    }

    public String getDispenseMessage() {
        return this.dispenseMessage;
    }

}
