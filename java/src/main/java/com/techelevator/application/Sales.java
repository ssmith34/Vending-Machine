package com.techelevator.application;

import java.util.HashMap;
import java.util.Map;

public class Sales {

    private double totalSales;
    private double totalMoneyInserted;
    private int itemsSold;
    private Map<String, Integer> soldAtDiscount = new HashMap<>();

    public void setMoneyInserted(double moneyIn) {
        this.totalMoneyInserted += moneyIn;
    }

    public static double getTotalMoneyInserted() {
        return totalMoneyInserted;
    }
}
