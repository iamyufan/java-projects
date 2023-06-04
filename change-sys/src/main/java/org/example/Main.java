package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        String details =
                "---------- Details ----------\n" +
                "Task\t\tAmount\tTimestamp\t\t\tRemaining\n";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        double totalMoneyAmount = 0;

        do {
            System.out.println("\n========== Mini Accounting System ==========");
            System.out.println("\t\t\t\t1  Check Info");
            System.out.println("\t\t\t\t+  Save Money");
            System.out.println("\t\t\t\t-  Spend Money");
            System.out.println("\t\t\t\t0  Exit");

            System.out.print("Input the number to specify your task: ");
            String input = scanner.next();

            switch (input) {
                case "1" :
                    System.out.println(details);
                    break;
                case "+" :
                    System.out.print("> How much money do you want to save: ");
                    double saveMoneyAmount = scanner.nextDouble();
                    if (saveMoneyAmount <= 0 || saveMoneyAmount > totalMoneyAmount) {
                        System.out.println("Invalid number :(");
                        break;
                    }
                    totalMoneyAmount += saveMoneyAmount;
                    Date saveMoneyDate = new Date(); // Get the current timestamp

                    details += "Money Saved\t+" + saveMoneyAmount +"\t" + sdf.format(saveMoneyDate) + "\t" + totalMoneyAmount + "\n";
                    break;
                case "-" :
                    System.out.print("> How much money do you want to spend: ");
                    double spendMoneyAmount = scanner.nextDouble();
                    if (!(spendMoneyAmount > 0)) {
                        System.out.println("Invalid number :(");
                        break;
                    }
                    System.out.print("> Where did you spend this money on: ");
                    String spendMoneyFor = scanner.next();

                    Date spendMoneyDate = new Date();

                    details += spendMoneyFor + "\t-" + spendMoneyAmount + "\t" + sdf.format(spendMoneyDate) + "\t" + totalMoneyAmount + "\n";
                    break;
                case "0" :
                    boolean exit = true;
                    do {
                        System.out.print("Confirm that you want to exit the system (y/n): ");
                        String userInput = scanner.next();
                        switch (userInput) {
                            case "y":
                                exit = true;
                                loop = false;
                            case "n":
                                continue;
                            default:
                                exit = false;
                        }
                    } while (!exit);
                    break;
                default:
                    System.out.println("You have input an invalid input :(");
            }
        } while (loop);
    }
}