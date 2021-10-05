package model;

public class ItemDetails {
    private String itemId;
    private int quantity;
    private double discount;

    public ItemDetails() {
    }

    public ItemDetails(String itemId, int quantity, double discount) {
        this.setItemId(itemId);
        this.setQuantity(quantity);
        this.setDiscount(discount);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                ", discount=" + discount +
                '}';
    }
}