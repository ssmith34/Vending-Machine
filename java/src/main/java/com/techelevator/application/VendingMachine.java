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

    List<Items> items = new ArrayList<>();

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

    public void run()
    {
        loadFile();
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                for(Items item : items)
                    System.out.println(item);

            }
            else if(choice.equals("purchase"))
            {
                UserInput.displayPurchaseMessage();
            }
            else if(choice.equals("sales report"))
            {
                // print sales report
            }
            else if(choice.equals("exit"))
            {
                // good bye
                break;
            }
        }
    }
}
