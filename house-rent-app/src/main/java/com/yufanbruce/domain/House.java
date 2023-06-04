package com.yufanbruce.domain;

public class House {
    private int id;
    private String owner;
    private String mobile;
    private String address;
    private int rent;
    private boolean hasRent;

    public House(int id, String owner, String mobile, String address, int rent, boolean hasRent) {
        this.id = id;
        this.owner = owner;
        this.mobile = mobile;
        this.address = address;
        this.rent = rent;
        this.hasRent = hasRent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public boolean isHasRent() {
        return hasRent;
    }

    public void setHasRent(boolean hasRent) {
        this.hasRent = hasRent;
    }

    //   id   owner   mobile   address   rent   status
    @Override
    public String toString() {
        String statusStr = this.hasRent ? "Rent Out" : "Not Leased";
        return this.id + "\t" +
                this.owner + "\t" +
                this.mobile + "\t" +
                this.rent + "\t" +
                statusStr + "\t" +
                this.address;
    }
}
