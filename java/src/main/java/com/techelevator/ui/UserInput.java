package com.techelevator.ui;

import com.techelevator.application.Sales;
import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput {

    private Sales sales = new Sales();

    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption() {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim();

        if (option.equalsIgnoreCase("D")) {
            return "display";
        } else if (option.equalsIgnoreCase("P")) {
            return "purchase";
        } else if (option.equalsIgnoreCase("E")) {
            return "exit";
        } else if (option.equalsIgnoreCase("S")) {
            return "sales report";
        } else {
            return "";
        }
    }

    public void displayPurchaseMessage() {

        while (true) {
            System.out.println("(M) Feed Money");
            System.out.println("(S) Select Item");
            System.out.println("(F) Finish Transaction\n");

            System.out.println("Current Money Provided: $" + sales.getTotalMoneyInserted());
            System.out.println();
            System.out.print("Please select an option: ");

            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim();

            if (option.equalsIgnoreCase("M")) {
                feedMoneyPrompt();

            } else if (option.equalsIgnoreCase("S")) {
                System.out.println("What item would you like to purchase? Please enter the slot number: ");
                String itemChosen = scanner.nextLine();
                VendingMachine.getItem(itemChosen);

            } else if (option.equalsIgnoreCase("F")) {
                return;
            }
        }
    }

        public void feedMoneyPrompt () {
            System.out.println("How much money would you like to insert? ($1, $5, $10, $20 only): ");
            String moneyIn = scanner.nextLine();
            int dollarsIn = Integer.parseInt(moneyIn);
            switch (dollarsIn) {
                case 1:
                    sales.setMoneyInserted(1.00);
                    break;
                case 5:
                    sales.setMoneyInserted(5.00);
                    break;
                case 10:
                    sales.setMoneyInserted(10.00);
                    break;
                case 20:
                    sales.setMoneyInserted(20.00);
                    break;
                default:
                    System.out.println("Invalid amount. Please select $1, $5, $10, $20");
            }
        }
        return "";
    }
