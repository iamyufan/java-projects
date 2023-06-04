package com.yufanbruce.utils;

import java.util.Scanner;

public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Read an integer from user's keyboard input
     * @return: the input integer
     */
    public static int readInt() {
        int result;
        while (true) {
            String input = readKeyboard(10, false);
            try {
                result = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print("> Invalid integer received. Please re-enter: ");
            }
        }
        return result;
    }

    /**
     * Read an integer from user's keyboard input but with a default value
     * @param defaultValue: if the input is empty, then return this value
     * @return
     */
    public static int readInt(int defaultValue) {
        int result;
        while (true) {
            String input = readKeyboard(10, true);
            if (input.equals("")) return defaultValue;
            try {
                result = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.print("> Invalid integer received. Please re-enter: ");
            }
        }
        return result;
    }

    public static String readString(int limit) {
        return readKeyboard(limit, false);
    }

    public static String readString(int limit, String defaultValue) {
        String input = readKeyboard(limit, true);
        return input.equals("") ? defaultValue : input;
    }

    public static boolean readBoolean() {
        while (true) {
            String input = readKeyboard(1, false);
            switch (input) {
                case "1": return true;
                case "0": return false;
                default: System.out.println("> Invalid input received. Please re-enter (1 or 0 only): ");
            }
        }
    }

    public static boolean readBoolean(boolean defaultValue) {
        boolean result;
        while (true) {
            String input = readKeyboard(1, true);
            if (input.equals("")) return defaultValue;
            try {
                result = Boolean.parseBoolean(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("> Invalid input received. Please re-enter (1 or 0 only): ");
            }
        }
        return result;
    }

    public static boolean readConfirmYN() {
        while (true) {
            System.out.print("> Confirm that you want to exit the system (y/n): ");
            String userInput = Utility.readKeyboard(1000, true);
            switch (userInput) {
                case "y":
                    return true;
                case "n":
                    return false;
            }
        }
    }

    /**
     * Read from user's input from keyboard
     * @param limit: the maximum length of input allowed
     * @param returnBlank: whether empty input is allowed
     * @return: the input as String
     */
    private static String readKeyboard(int limit, boolean returnBlank) {
        String line = "";

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // If the input is empty
            if (line.length() == 0) {
                if (returnBlank) return line;   // return empty if returnBlank is allowed
                else {
                    System.out.print("> Input cannot be empty. Please re-enter: ");
                    continue;                  // continue to receive user's input since returnBlank is not allowed
                }
            }
            // If the input is longer than the input
            else if (line.length() > limit) {
                System.out.print("> Input is out of limit. Please re-enter: ");
                continue;
            }
            break;
        }
        return line;
    }
}
