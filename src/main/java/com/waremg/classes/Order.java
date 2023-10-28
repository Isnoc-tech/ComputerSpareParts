package com.waremg.classes;

public class Order {
    private int orderID;
    private String description;
    private int quantity;
    private double unitPrice;

    public Order(int orderID, String description, int quantity, double unitPrice) {
        this.orderID = orderID;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Order [orderID=" + orderID + ", description=" + description + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
    }
}
