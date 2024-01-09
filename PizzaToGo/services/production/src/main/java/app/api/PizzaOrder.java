package app.api;
import com.google.gson.annotations.SerializedName;

public class PizzaOrder {
    private String orderID;
    private String userID;
    private String[] ingredients;

    public PizzaOrder(String orderID, String userID, String[] ingredients) {
        this.orderID = orderID;
        this.userID = userID;
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }
    public String getOrderID() {
        return orderID;
    }
    public String getUserID() {
        return userID;
    }
}