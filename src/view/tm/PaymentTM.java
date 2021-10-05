package view.tm;

public class PaymentTM {
    private String orderId;
    private String custId;
    private String date;
    private String time;
    private double cost;

    public PaymentTM(String orderId, String custId, String date, String time, String total) {
    }

    public PaymentTM(String orderId, String custId, String date, String time, double cost) {
        this.setOrderId(orderId);
        this.setCustId(custId);
        this.setDate(date);
        this.setTime(time);
        this.setCost(cost);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
