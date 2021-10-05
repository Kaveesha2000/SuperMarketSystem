package model;

import java.util.Date;

public class IncomeReport {
    private String date;
    private int numberOfOrders;
    private int numberOfSoldItem;
    private double finalCost;
    // String monthORyear;

   /* public IncomeReports(Date date, int numberOfOrders, int numberOfSoldItem, double finalCost) {
        this.setDate(date);
        this.setNumberOfOrders(numberOfOrders);
        this.setNumberOfSoldItem(numberOfSoldItem);
        this.setFinalCost(finalCost);
    }*/

    /*public IncomeReports(Date date, int numberOfOrders, double finalCost) {
        this.date = date;
        this.numberOfOrders = numberOfOrders;
        this.finalCost = finalCost;
    }*/

    public IncomeReport(String date, int numberOfSoldItem) {
        this.date = date;
        this.numberOfSoldItem = numberOfSoldItem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public int getNumberOfSoldItem() {
        return numberOfSoldItem;
    }

    public void setNumberOfSoldItem(int numberOfSoldItem) {
        this.numberOfSoldItem = numberOfSoldItem;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    @Override
    public String toString() {
        return "IncomeReports{" +
                "date=" + date +
                ", numberOfOrders=" + numberOfOrders +
                ", numberOfSoldItem=" + numberOfSoldItem +
                ", finalCost=" + finalCost +
                '}';
    }
}
