package com.techelevator;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Drink;
import com.techelevator.models.Items;
import com.techelevator.models.Munchy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new VendingMachine();
    }

    @Test
    public void getItem_should_remove_item_from_list() {
        List<Items> input = vendingMachine.getItems();
        int expected = 5;
        int actual = 0;
        vendingMachine.loadFile();
        vendingMachine.getItem("A1");
        for(Items item : input) {
            if(item.getSlotNumber().equals("A1")) {
                actual = item.getAmountLeft();
            }
        }
        Assert.assertEquals(expected, actual);
    }
}
