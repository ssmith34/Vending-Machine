package com.techelevator.ui;

import com.techelevator.application.Sales;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim();

        if (option.equalsIgnoreCase("D"))
        {
            return "display";
        }
        else if (option.equalsIgnoreCase("P"))
        {
            return "purchase";
        }
        else if (option.equalsIgnoreCase("E"))
        {
            return "exit";
        }
        else if (option.equalsIgnoreCase("S"))
        {
            return "sales report";
        }
        else
        {
            return "";
        }
    }

    public static String displayPurchaseMessage() {
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction\n");
        System.out.println("Current Money Provided: $" + Sales.getTotalMoneyInserted());
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim();

        if(option.equalsIgnoreCase("M")) {
            // feed money into machine
        }
        else if(option.equalsIgnoreCase("S")) {
            // purchase item
        }
        else if(option.equalsIgnoreCase("F")) {
            // Finish Transaction
        }
        else {
            return "";
        }
        return "";
    }
}
