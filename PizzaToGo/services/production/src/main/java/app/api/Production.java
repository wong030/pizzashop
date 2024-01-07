import com.google.gson.Gson;
public class Production {
    private String[] ingredients;
    private String orderID;
    private String userID;

    public Production(String[] ingredients, String orderID, String userID) {
        this.ingredients = ingredients;
        this.orderID = orderID;
        this.userID = userID;
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

    public static Production fromJsonString(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Production.class);
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}