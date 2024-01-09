import com.google.gson.Gson;

public class PizzaShop {
    private String orderID;
    private String userID;
    private String[] ingredients;

    public PizzaShop(String orderID, String userID, String[] ingredients) {
        this.orderID = orderID;
        this.userID = userID;
        this.ingredients = ingredients;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getUserID() {
        return userID;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public static void main(String[] args) {
    
        PizzaShop pizzaOrder = new PizzaShop("12345", "user123", new String[]{"Tomato", "Cheese", "Pepperoni"});
        Gson gson = new Gson();
        String json = gson.toJson(pizzaOrder);
        System.out.println("Gson-String: " + json);
        PizzaProduktion.processOrder(json);
    }
}

