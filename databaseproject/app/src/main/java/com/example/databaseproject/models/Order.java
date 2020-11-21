package com.example.databaseproject.models;

public class Order {

    String itemName;
    String itemQty;

    public Order(String itemName, String itemQty) {
        this.itemName = itemName;
        this.itemQty = itemQty;
    }
public Order(){}
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }
}
