package com.techelevator.application;

import java.util.HashMap;
import java.util.Map;

public class Sales {

    private double totalSales;
    private int itemsSold;
    private Map<String, Integer> soldAtDiscount = new HashMap<>();

    public void setTotalSales(double newSale) {
        totalSales += newSale;
    }

    public void setSoldAtDiscount(String item, Integer itemsSoldAtDiscount) {
        if(soldAtDiscount.get(item) == null)
            soldAtDiscount.put(item, itemsSoldAtDiscount);
        else soldAtDiscount.put(item, soldAtDiscount.get(item) + 1);
    }
}
