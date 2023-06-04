package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MiniAccountBook {
    private String name;
    private double balance;
    private String details =
            "\n---------------------------- Details ----------------------------\n" +
            "Activity\t\tAmount\t\tTimestamp\t\tBalance\n";
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public MiniAccountBook(String name, double balance) {
        this.name = name;
        this.balance = balance;
        System.out.println("========== Mini Accounting System ==========");
        System.out.println("========== Greeting! " + this.name);
        do {
            run();
        } while (this.running);
    }

    public MiniAccountBook(String name) {
        this(name, 0.0);
    }

    protected void run() {
        System.out.println("\n============================================");
        System.out.println("\t\t1  Check details of your account book");
        System.out.println("\t\t+  Record a money save");
        System.out.println("\t\t-  Record a money spend");
        System.out.println("\t\t0  Exit");

        System.out.print("Input the number to specify: ");
        String input = scanner.next();

        switch (input) {
            case "1":
                this.printDetails();
                break;
            case "+":
                this.saveMoney();
                break;
            case "-":
                this.spendMoney();
                break;
            case "0":
                this.exit();
                break;
            default:
                System.out.println("> Invalid number!");
                break;
        }
    }

    public void saveMoney() {
        double amount = this.readAmount("save");
        if (amount <= 0) {
            System.out.println("> Invalid number!");
            return;
        }
        this.balance += amount;
        this.details += "Save\t\t+" + amount + "\t\t" + this.getCurTimestamp() + "\t\t" + this.balance + "\n";
        return;
    }

    public void spendMoney() {
        double amount = this.readAmount("spend");
        if (amount <= 0 || amount > this.balance) {
            System.out.println("> Invalid number!");
            return;
        }
        this.balance -= amount;
        System.out.print("> Input what you spend money for: ");
        String spendFor = scanner.next();
        this.details += spendFor + "\t\t-" + amount + "\t\t" + this.getCurTimestamp() + "\t\t" + this.balance + "\n";
        return;
    }

    public void printDetails() {
        System.out.println(details + "\n");
        return;
    }

    public void exit() {
        boolean loop = false;
        do {
            System.out.print("> Confirm that you want to exit the system (y/n): ");
            String userInput = scanner.next();
            switch (userInput) {
                case "y":
                    this.running = false;
                    loop = false;
                    break;
                case "n":
                    loop = false;
                    break;
                default:
                    loop = true;
                    break;
            }
        } while (loop);
    }

    protected double readAmount(String activity) {
        System.out.print("> Input the amount of money you " + activity + ": ");
        return scanner.nextDouble();
    }

    protected String getCurTimestamp() {
        Date now = new Date();
        return sdf.format(now);
    }
}
