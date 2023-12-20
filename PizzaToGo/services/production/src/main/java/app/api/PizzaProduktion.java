import com.google.gson.Gson;
    
public class Orderlist {
    private String[] ingredients;

    public Orderlist(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }
}

public class Auslieferung {
    private String orderID;
    private String userID;

    public Auslieferung(String orderID, String userID) {
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

public class PizzaProduktion {
    public static void processOrder(String gsonString) {
        
        Gson gson = new Gson();

        PizzaShop pizzaOrder = gson.fromJson(gsonString, PizzaShop.class);

        String[] ingredients = pizzaOrder.getIngredients();
        String orderID = pizzaOrder.getOrderID();
        String userID = pizzaOrder.getUserID();

        Orderlist orderlist = new Orderlist(ingredients);
        Auslieferung auslieferung = new Auslieferung(orderID, userID);

        String jsonOrderlist = gson.toJson(orderlist);
        String jsonAuslieferung = gson.toJson(auslieferung);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Beschaffungswesen.processOrder(jsonOrderlist);
        Auslieferung.logistik.processOrder(jsonAuslieferung);;
    }
}