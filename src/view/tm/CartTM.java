package view.tm;

public class CartTM {
    private Double discount;
    private String itemId;
    private Integer quantity;
    private Double unitPrice;
    private Double total;
    private Double finalTotal;

    public CartTM(Double discount, String itemId, int quantity, double unitPrice, Double total) {
        this.setDiscount(discount);
        this.setItemId(itemId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
    }

    /*public CartTM(Double discount, String itemId, Integer quantity, Double unitPrice, Double total, Double finalTotal) {
        this.setDiscount(discount);
        this.setItemId(itemId);
        this.setQuantity(quantity);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
        this.setFinalTotal(finalTotal);
    }*/

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(Double finalTotal) {
        this.finalTotal = finalTotal;
    }
}
