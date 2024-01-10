package app.api;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/production")
public class ProductionController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String doProduction(String parameter) {
        // Hier wird der Code der ProductionController-Klasse eingefügt
        processOrder(parameter);
        return String.format("Processed parameter value '%s'", parameter);
    }

    private static Boolean processOrder(String gsonString) {

        Gson gson = new Gson();
        /*PizzaOrder pizzaOrder = gson.fromJson(pizzaOrder,PizzaOrder.class);
        String [] ingredient = {"Zwiebel","Pillze", "Teig", "Soße"};
        PizzaOrder pizzaOrder = new PizzaOrder("12", "26",ingredient );
        
        String[] ingredients = pizzaOrder.getIngredients();
        String orderID = pizzaOrder.getOrderID();
        String userID = pizzaOrder.getUserID();
*/
        String jsonBody = toJsonString(gsonString);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> parsedJson;
        String orderId = null;
        String date = null;
        String time = null;
        String userId = null; 
        String pizza = null;
        double price = 0.0;
        String size = null;

        String ingredient1Id = null;
        String ingredient2Id = null;
        String ingredient3Id = null;
        String ingredient4Id = null;
        String ingredient5Id = null;
        String ingredient6Id = null;
        String ingredient7Id = null;
        String ingredient8Id = null;

        try {
            parsedJson = objectMapper.readValue(jsonBody, Map.class);
            orderId = (String) parsedJson.get("orderID");
            date = (String) parsedJson.get("date");
            time = (String) parsedJson.get("time");
            userId = (String) parsedJson.get("userID");
            pizza = (String) parsedJson.get("pizza");
            price = (Double) parsedJson.get("price");
            size = (String) parsedJson.get("size");
            ingredient1Id = ((String) parsedJson.get("ingredient1_id"));
            ingredient2Id = ((String) parsedJson.get("ingredient2_id"));
            ingredient3Id = ((String) parsedJson.get("ingredient3_id"));
            ingredient4Id = ((String) parsedJson.get("ingredient4_id"));
            ingredient5Id = ((String) parsedJson.get("ingredient5_id"));
            ingredient6Id = ((String) parsedJson.get("ingredient6_id"));
            ingredient7Id = ((String) parsedJson.get("ingredient7_id"));
            ingredient8Id = ((String) parsedJson.get("ingredient8_id"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String [] ingredients = {ingredient1Id, ingredient2Id, ingredient3Id,ingredient4Id,ingredient5Id,ingredient6Id,ingredient7Id,ingredient8Id};
        //Procurement procurementList = new Procurement(ingredients);
        //String jsonProcurement = gson.toJson(procurementList);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //neuer Code
        Delivery deliverySend = new Delivery(orderId, userId);
        HttpClient clientDelivery = HttpClient.newHttpClient();

        Map<String, Object> deliveryto = new HashMap<>();
        deliveryto.put("orderID", deliverySend.getOrderID());
        deliveryto.put("userID", deliverySend.getUserID());

        String jsonBodydelivery = toJsonString(deliveryto);
        URI uriDelivery = URI.create("http://localhost:9081/Delivery");

        HttpRequest requestdelivery = HttpRequest.newBuilder()
                .uri(uriDelivery)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBodydelivery))
                .build();

        try{
            HttpResponse<String> responsedelivery = clientDelivery.send(requestdelivery, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + responsedelivery.statusCode());
            System.out.println("Response Body: " + responsedelivery.body());

            return Boolean.parseBoolean(responsedelivery.body());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        //Procurment
        Procurement procurementSend = new Procurement(ingredients);
        HttpClient clientProcurment = HttpClient.newHttpClient();
        Map<String, Object> procurmentto = new HashMap<>();
        procurmentto.put("ingredients", procurementSend.getIngredients());

        String jsonBodyprocurment = toJsonString(procurmentto);
        URI uriProcurment = URI.create("http://localhost:9081/Procurement");

        HttpRequest requestprocurment = HttpRequest.newBuilder()
                .uri(uriProcurment)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBodyprocurment))
                .build();
        try{
            HttpResponse<String> requestprocurment = clientProcurment.send( requestprocurment, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " +  requestprocurment.statusCode());
            System.out.println("Response Body: " +  requestprocurment.body());

            return Boolean.parseBoolean( requestprocurment.body());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private static String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Fehler beim Konvertieren in JSON", e);
        }
    }
}