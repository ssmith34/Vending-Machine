package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine<Item>
{
    Sales sales = new Sales();
    UserInput userInput = new UserInput();
    List<Items> items = new ArrayList<>();
    private double moneyInserted;
    private String chosenItem;
    private int itemsPurchased;
    private static final int SOLD_PER_DISCOUNT = 1;
    private static final double DISCOUNT_AMOUNT = 1.00;

    public void loadFile() {
        File file = new File("catering.csv");
        try(Scanner fileScanner = new Scanner(file)) {

            while(fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] lineArr = line.split("\\,");

                if(lineArr[3].equals("Munchy")) {
                    Items munchy = new Munchy(lineArr[1], Double.parseDouble(lineArr[2]), lineArr[0], "Munchy, Munchy, so Good!");
                    items.add(munchy);
                } else if(lineArr[3].equals("Candy")) {
                    Items candy = new Candy(lineArr[1], Double.parseDouble(lineArr[2]), lineArr[0], "Sugar, Sugar, so Sweet!");
                    items.add(candy);
                } else if(lineArr[3].equals("Drink")) {
                    Items drink = new Drink(lineArr[1], Double.parseDouble(lineArr[2]), lineArr[0], "Drinky, Drinky, Slurp Slurp!");
                    items.add(drink);
                } else {
                    Items gum = new Gum(lineArr[1], Double.parseDouble(lineArr[2]), lineArr[0], "Chewy, Chewy, Lots O Bubbles!");
                    items.add(gum);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Vending machine 404 error.");
        }
    }

    public void run() {
        loadFile();
        while (true) {
            UserOutput.displayHomeScreen();
            UserOutput.displayMainMenu();
            String choice = UserInput.getMainMenuOption();

            if (choice.equals("display")) {
                UserOutput.displayVendingMachineItems(items);
            } else if (choice.equals("purchase")) {
                boolean keepGoing = true;
                while (keepGoing) {
                    UserOutput.displayPurchaseMenu(moneyInserted);
                    choice = UserInput.getPurchaseMenuOption();
                    switch (choice) {
                        case "Feed Money":
                            UserOutput.displayMoneyMenu();
                            moneyInserted += UserInput.getMoneyOption();
                            break;
                        case "Select Item":
                            UserOutput.displayVendingMachineItems(items);
                            UserOutput.displayItemPrompt();
                            chosenItem = UserInput.getItemOption();
                            getItem(chosenItem);
                            break;
                        case "Finish":
                            // reset itemsPurchased
                            keepGoing = false;

                    }
                }
            }
            else if (choice.equals("sales report")) {
                    // print sales report
            } else if (choice.equals("exit")) {
                    // good bye
                    break;
            }
        }
    }
    private void getItem (String chosenItem){
        for(Items item : items) {
            if(chosenItem.equals(item.getSlotNumber()) && item.getAmountLeft() == 0) {
                System.out.println("That item is no longer available, please choose again.");
                return;
            }
            if(chosenItem.equals(item.getSlotNumber())) {
                itemsPurchased++;
                if(itemsPurchased % 2 == 0) {
                    item.removeItem();
                    sales.setTotalSales(item.getPrice() - DISCOUNT_AMOUNT);
                    sales.setSoldAtDiscount(item.getName(), SOLD_PER_DISCOUNT);
                    moneyInserted -= item.getPrice() + DISCOUNT_AMOUNT;
                }
                else{
                    item.removeItem();
                    sales.setTotalSales(item.getPrice());
                    sales.setSoldAtDiscount(item.getName(), SOLD_PER_DISCOUNT);
                    moneyInserted -= item.getPrice();
                }
            }
        }
    }

}


