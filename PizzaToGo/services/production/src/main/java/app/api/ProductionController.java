package app.api;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/production")
public class ProductionController {

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doProduction(@PathParam("parameter") String parameter) {
        // Hier wird der Code der ProductionController-Klasse eingefügt
        processOrder(parameter);
        return String.format("Processed parameter value '%s'", parameter);
    }

    private static void processOrder(String gsonString) {

        Gson gson = new Gson();
        //PizzaOrder pizzaOrder = gson.fromJson(pizzaOrder,PizzaOrder.class);
        String [] ingredient = {"Zwiebel","Pillze", "Teig", "Soße"};
        PizzaOrder pizzaOrder = new PizzaOrder("12", "26",ingredient );
        
        String[] ingredients = pizzaOrder.getIngredients();
        String orderID = pizzaOrder.getOrderID();
        String userID = pizzaOrder.getUserID();

        Procurement procurementList = new Procurement(ingredients);
        Delivery deliveryList = new Delivery(orderID, userID);

        String jsonProcurement = gson.toJson(procurementList);
        String jsonDelivery = gson.toJson(deliveryList);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //ProcurementController.processOrder(jsonProcurement);
        //DeliveryController.processOrder(jsonDelivery);
    }
}