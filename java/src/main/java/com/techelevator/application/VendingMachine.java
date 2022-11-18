package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine
{
    Sales sales = new Sales();
    Audit audit = new Audit("Audit.txt");
    private List<Items> items = new ArrayList<>();
    private double moneyInserted;
    private double totalMoney;
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
                    UserOutput.displayPurchaseMenu(totalMoney);
                    choice = UserInput.getPurchaseMenuOption();
                    switch (choice) {
                        case "Feed Money":
                            UserOutput.displayMoneyMenu();
                            moneyInserted = UserInput.getMoneyOption();
                            totalMoney += moneyInserted;
                            if(moneyInserted != 0.00) {
                                // Ten spaces for spacing
                                audit.write("MONEY FED:          $"
                                        + String.format("%.2f", moneyInserted) + "    $"
                                        + String.format("%.2f", totalMoney));
                            }
                            break;
                        case "Select Item":
                            UserOutput.displayVendingMachineItems(items);
                            UserOutput.displayItemPrompt();
                            chosenItem = UserInput.getItemOption();
                            getItem(chosenItem);
                            break;
                        case "Finish":
                            if(totalMoney > 0.0) {
                                getChange();
                            }

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
    public void getItem (String chosenItem){
        for(Items item : items) {
            if(chosenItem.equalsIgnoreCase(item.getSlotNumber()) && item.getAmountLeft() == 0) {
                System.out.println("That item is no longer available, please choose again.");
                return;
            }
            if(chosenItem.equalsIgnoreCase(item.getSlotNumber())) {
                itemsPurchased++;
                double beforePurchaseTotal = totalMoney;
                if(itemsPurchased % 2 == 0) {
                    item.removeItem();
                    sales.setTotalSales(item.getPrice() - DISCOUNT_AMOUNT);
                    sales.setSoldAtDiscount(item.getName(), SOLD_PER_DISCOUNT);
                    totalMoney -= (item.getPrice() - DISCOUNT_AMOUNT);
                    System.out.println("Dispensing " + item.getName() + " for $"
                            + (item.getPrice() - DISCOUNT_AMOUNT) + ", money remaining: $" +
                            String.format("%.2f", totalMoney));
                    System.out.println(item.getDispenseMessage());
                    audit.write(item.getName() + "        " + item.getSlotNumber() + " $"
                            + String.format("%.2f", beforePurchaseTotal) + "    $"
                            + String.format("%.2f", totalMoney));
                    return;
                }
                else {
                    item.removeItem();
                    sales.setTotalSales(item.getPrice());
                    sales.setSoldAtDiscount(item.getName(), SOLD_PER_DISCOUNT);
                    totalMoney -= item.getPrice();
                    System.out.println("Dispensing " + item.getName() + " for $"
                            + item.getPrice() + ", money remaining: $" + String.format("%.2f", totalMoney));
                    System.out.println(item.getDispenseMessage());
                    audit.write(item.getName() + "        " + item.getSlotNumber() + " $"
                            + String.format("%.2f", beforePurchaseTotal) + "    $"
                            + String.format("%.2f", totalMoney));
                    return;
                }
            }
        }
        System.out.println("Invalid choice. Please try again.");
    }

    public void getChange() {
        double changeDue = totalMoney;
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int moneyInt = (int)((totalMoney + 0.0001) * 100);
        while(moneyInt > 0) {
            if (moneyInt >= 100) {
                dollars = moneyInt / 100;
                moneyInt -= (dollars * 100);
            }
            if(moneyInt >= 25) {
                quarters = moneyInt / 25;
                moneyInt -= (quarters * 25);
            }
            if(moneyInt >= 10) {
                dimes = moneyInt / 10;
                moneyInt -= (dimes * 10);
            }
            if(moneyInt >= 5) {
                nickels = moneyInt / 5;
                moneyInt -= (nickels * 5);
            }
        }
        audit.write("CHANGE GIVEN:          $" + totalMoney + "    $" + "0.00");
        UserOutput.displayChangeMessage(dollars, quarters, dimes, nickels, changeDue);
        totalMoney = 0.0;
        moneyInserted = 0.0;
    }

    public List<Items> getItems() {
        return items;
    }

}


