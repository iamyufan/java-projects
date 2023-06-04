package com.yufanbruce.service;

import com.yufanbruce.domain.House;

/**
 * To handle the CRUD operations of the houses
 */
public class HouseService {
    private House[] houses;
    private int houseCount;
    private int maxId;

    public HouseService(int maxSize) {
        this.houses = new House[maxSize];
        this.houseCount = 0;
        this.maxId = 0;
    }

    public int addHouse(House house) {
        if (houseCount == this.houses.length)   return -1;
        house.setId(++maxId);
        this.houses[houseCount++] = house;
        return maxId;
    }

    public boolean editHouse(House houseToEdit, String newOwner, String newMobile, String newAddress, int newRent, boolean newHasRent) {
        houseToEdit.setOwner(newOwner);
        houseToEdit.setMobile(newMobile);
        houseToEdit.setAddress(newAddress);
        houseToEdit.setRent(newRent);
        houseToEdit.setHasRent(newHasRent);
        return true;
    }

    public House findHouseById(int houseId) {
        for (int i = 0; i < houseCount; i++) {
            if (houses[i].getId() == houseId) {
                return houses[i];
            }
        }
        return null;
    }

    public boolean delHouse(int houseId) {
        for (int i = 0; i < houseCount; i++) {
            if (houses[i].getId() == houseId) {
                for (int j = i; j < houseCount-1; j++) {
                    houses[j] = houses[j+1];
                }
                houses[--houseCount] = null;
                return true;
            }
        }
        return false;
    }

    public House[] listHouses() {
        return houses;
    }
}
