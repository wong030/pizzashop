package app.api;
public class Delivery {
    private String orderID;
    private String userID;

    public Delivery(String orderID, String userID) {
        this.orderID = orderID;
        this.userID = userID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }
}