package com.yufanbruce.view;

import com.yufanbruce.domain.House;
import com.yufanbruce.service.HouseService;
import com.yufanbruce.utils.Utility;

/**
 * To handle the direct interactions with users
 */
public class HouseView {
    private boolean loop = true;
    private HouseService houseService = new HouseService(10);

    /**
     * Display the main menu
     * ====== Mini House Renting App =====
     * ====== Select your operation ======
     *      1. List all houses
     *      2. Find houses
     *      3. Delete a house
     *      4. Edit a house info
     *      5. Add a house
     *      0. Exit the app
     */
    public void mainMenu() {
        do {
            System.out.println("\n====== Mini House Renting App =====");
            System.out.println("====== Select your operation ======");
            System.out.println("\t\t1. List all houses");
            System.out.println("\t\t2. Find houses");
            System.out.println("\t\t3. Delete a house");
            System.out.println("\t\t4. Edit a house info");
            System.out.println("\t\t5. Add a house");
            System.out.println("\t\t0. Exit the app");
            System.out.print("Input the number (0-5): ");
            int operation = Utility.readInt();  // Read user input
            switch (operation) {
                case 1:
                    System.out.println("> Listing all houses...");
                    listHouses();
                    break;
                case 2:
                    System.out.println("> Finding a house with id...");
                    findHouse();
                    break;
                case 3:
                    System.out.println("> Deleting a house...");
                    delHouse();
                    break;
                case 4:
                    System.out.println("> Editing a house info...");
                    editHouse();
                    break;
                case 5:
                    System.out.println("> Adding a house...");
                    addHouse();
                    break;
                case 0:
                    System.out.println("> Exiting the app...");
                    exitApp();
                    break;
                default:
                    System.out.println("> Invalid number");
            }
            System.out.println("---------------------------------------------------------------------");
        } while (loop);
    }

    /**
     * List the house information of all houses
     * id   owner   mobile  rent   status   address
     */
    private void listHouses() {
        House[] houseList = houseService.listHouses();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("id\towner\tmobile\trent\tstatus\t\taddress");
        System.out.println("---------------------------------------------------------------------");
        for (int i = 0; i < houseList.length; i++) {
            if (houseList[i] == null) break;
            System.out.println(houseList[i]);
        }
    }

    /**
     * Add a house by asking user to input the needed info
     */
    private void addHouse() {
        System.out.print("> Input the owner of the house: ");
        String owner = Utility.readString(30);
        System.out.print("> Input the mobile of the owner: ");
        String mobile = Utility.readString(30);
        System.out.print("> Input the address of the house: ");
        String address = Utility.readString(100);
        System.out.print("> Input the monthly rent of the house: ");
        int rent = Utility.readInt();
        System.out.print("> Input if the house has been rent out (1 for rent out, 0 for not leased): ");
        boolean hasRent = Utility.readBoolean();
        int newHouseId = houseService.addHouse(new House(0, owner, mobile, address, rent, hasRent));
        // If the house is not successfully added
        if (newHouseId == -1) {
            System.out.println("> House adding failed because maximum amount of houses has reached!");
            return;
        }
        System.out.println("> House adding succeeded!");
        House houseAdded = houseService.findHouseById(newHouseId);
        System.out.println(houseAdded);
    }

    /**
     * Delete a house info with the house id input from keyboard
     */
    private void delHouse() {
        System.out.print("> Input the house id that you want to delete (-1 to cancel): ");
        int houseId = Utility.readInt();
        if (houseId == -1) return;
        boolean deleteResult = houseService.delHouse(houseId);
        if (deleteResult)   System.out.println("> House with id " + houseId + " deletion succeeded!");
        else                System.out.println("> House with id " + houseId + " deletion failed because such house does not exist!");
    }

    /**
     * Find a house info with the house id input from keyboard
     */
    private void findHouse() {
        System.out.print("> Input the house id that you want to find (-1 to cancel): ");
        int houseId = Utility.readInt();
        if (houseId == -1) return;
        House houseFound = houseService.findHouseById(houseId);
        if (houseFound == null) System.out.println("> House with id " + houseId + " finding failed because such house does not exist!");
        else                    System.out.println(houseFound);
    }

    /**
     * Edit the house info of the given house id
     */
    private void editHouse() {
        System.out.print("> Input the house id that you want to edit (-1 to cancel): ");
        int houseId = Utility.readInt();
        if (houseId == -1) return;
        House houseFound = houseService.findHouseById(houseId);
        if (houseFound == null) {
            System.out.println("> House with id " + houseId + " edition failed because such house does not exist!");
            return;
        }
        // Get the current house info
        System.out.println("> The current house info:");
        System.out.println(houseFound);
        String curOwner = houseFound.getOwner();
        String curMobile = houseFound.getMobile();
        String curAddress = houseFound.getAddress();
        int curRent = houseFound.getRent();
        boolean curHasRent = houseFound.isHasRent();
        // Ask the user to update info
        System.out.print("> Update the owner (current: " + curOwner + "): ");
        String newOwner = Utility.readString(30, curOwner);
        System.out.print("> Update the mobile (current: " + curMobile + "): ");
        String newMobile = Utility.readString(30, curMobile);
        System.out.print("> Update the address (current: " + curAddress + "): ");
        String newAddress = Utility.readString(100, curAddress);
        System.out.print("> Update the rent (current: " + curRent + "): ");
        int newRent = Utility.readInt(curRent);
        System.out.print("> Update if the house has been rent out (1 for rent out, 0 for not leased): ");
        boolean newHasRent = Utility.readBoolean(curHasRent);
        // Edit the house info in the houseService object
        houseService.editHouse(houseFound, newOwner, newMobile, newAddress, newRent, newHasRent);
        System.out.println("> After editing: ");
        System.out.println(houseFound);     // Print the editted info
    }

    /**
     * Exit the app by double confirmation (y/n)
     */
    private void exitApp() {
        boolean confirmExit = Utility.readConfirmYN();
        if (confirmExit) this.loop = false;
    }
}
