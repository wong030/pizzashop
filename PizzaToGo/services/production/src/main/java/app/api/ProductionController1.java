

public class ProductionController {
    public static void processOrder(String gsonString) {

        Production pizzaOrder = PizzaOrder.fromJsonString(gsonString);

        String[] ingredients = pizzaOrder.getIngredients();
        String orderID = pizzaOrder.getOrderID();
        String userID = pizzaOrder.getUserID();

        Procurement procurementList = new Procurement(ingredients);
        Delivery deliveryList = new Delivery(orderID, userID);

        String jsonProcurement = procurementList.toJsonString();
        String jsonDelivery = deliveryList.toJsonString();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ProcurementController.processOrder(jsonProcurement);
        DeliveryController.processOrder(jsonDelivery);
    }
}
