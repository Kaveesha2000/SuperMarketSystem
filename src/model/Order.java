package model;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String date;
    private String time;
    private String customerId;
    private double cost;
    private ArrayList<ItemDetails> items;

    public Order() {
    }

    public Order(String orderId, String date, String time, String customerId, double cost, ArrayList<ItemDetails> items) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setTime(time);
        this.setCustomerId(customerId);
        this.setCost(cost);
        this.setItems(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemDetails> items) {
        this.items = items;
    }
}
