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

    private static Scanner scanner = new Scanner(System.in);

    public static String getMainMenuOption() {
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

    public static String getPurchaseMenuOption() {
            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim();

            if (option.equalsIgnoreCase("M")) {
                return "Feed Money";
            } else if (option.equalsIgnoreCase("S")) {
                return "Select Item";
            } else if (option.equalsIgnoreCase("F")) {
                return "Finish";
            } else {
                return "";
            }
    }

    public static double getMoneyOption() {
        String moneyIn = scanner.nextLine();
        int dollarsIn = Integer.parseInt(moneyIn);
        switch (dollarsIn) {
            case 1:
                return 1.00;
            case 5:
                return 5.00;
            case 10:
                return 10.00;
            case 20:
                return 20.00;
            default:
                System.out.println("Invalid amount. Please select $1, $5, $10, $20");
                return 0.00;
        }
    }

    public static String getItemOption (){
        String chosenItem = scanner.nextLine();
        return chosenItem;
    }
}
