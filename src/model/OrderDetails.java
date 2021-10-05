package model;

public class OrderDetails  {
    private String orderId;
    private String itemCode;
    private int quantity;
    private double discount;

    public OrderDetails() {
    }

    public OrderDetails(String orderId, String itemCode, int quantity, double discount) {
        this.setOrderId(orderId);
        this.setItemCode(itemCode);
        this.setQuantity(quantity);
        this.setDiscount(discount);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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
}
