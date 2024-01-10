package app.api;

import app.api.dto.CreateOrderDTO;
import app.dao.OrderDAO;
import app.model.Order;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Path("/production")
@Singleton
public class ProductionController {

    @Inject private OrderDAO orderDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doProduction(CreateOrderDTO createData) {
        try {
            final Order createdOrder = orderDAO.createOrder(createData);
            produceOrder();
            boolean deliveryBool = sendDelivery(createdOrder);
            boolean procurementBool = sendProcurment(createdOrder);
            System.out.println("Created Order: " + createdOrder);
            System.out.println("Delivery notified: " + deliveryBool);
            System.out.println("Procurment notified: " + procurementBool);
            return Response.ok().entity(createdOrder).build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.serverError().build();

        }

    }

    private boolean sendProcurment(Order createdOrder) {
        Map<String, Object> orderdto = new HashMap<>();
        orderdto.put("ingredient1_id", createdOrder.getIngredient1Id());
        orderdto.put("ingredient2_id", createdOrder.getIngredient2Id());
        orderdto.put("ingredient3_id", createdOrder.getIngredient3Id());
        orderdto.put("ingredient4_id", createdOrder.getIngredient4Id());
        orderdto.put("ingredient5_id", createdOrder.getIngredient5Id());
        orderdto.put("ingredient6_id", createdOrder.getIngredient6Id());
        orderdto.put("ingredient7_id", createdOrder.getIngredient7Id());
        orderdto.put("ingredient8_id", createdOrder.getIngredient8Id());

        // Konvertieren in JSON-String
        String jsonBody = toJsonString(orderdto);
        URI uriProcurment = URI.create("http://localhost:9083/api/procurement");


        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriProcurment)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try{
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " +  response.statusCode());
            System.out.println("Response Body: " +  response.body());

            return Boolean.parseBoolean( response.body());
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private  boolean sendDelivery(Order createdOrder) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Map<String, Object> orderdto = new HashMap<>();
        orderdto.put("orderID", createdOrder.getOrderId());
        orderdto.put("date", currentDateTime.toLocalDate().toString());
        orderdto.put("time", currentDateTime.toLocalTime().toString());
        orderdto.put("userID", createdOrder.getUserId());
        orderdto.put("pizza", createdOrder.getPizza());
        orderdto.put("price", createdOrder.getPrice());
        orderdto.put("size", createdOrder.getSize());
        orderdto.put("ingredient1_id", createdOrder.getIngredient1Id());
        orderdto.put("ingredient2_id", createdOrder.getIngredient2Id());
        orderdto.put("ingredient3_id", createdOrder.getIngredient3Id());
        orderdto.put("ingredient4_id", createdOrder.getIngredient4Id());
        orderdto.put("ingredient5_id", createdOrder.getIngredient5Id());
        orderdto.put("ingredient6_id", createdOrder.getIngredient6Id());
        orderdto.put("ingredient7_id", createdOrder.getIngredient7Id());
        orderdto.put("ingredient8_id", createdOrder.getIngredient8Id());

        // Konvertieren in JSON-String
        String jsonBody = toJsonString(orderdto);
        URI uriProcurment = URI.create("http://localhost:9084/api/delivery");


        HttpRequest request = HttpRequest.newBuilder()
                .uri(uriProcurment)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try{
            HttpResponse<String> response = client.send( request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " +  response.statusCode());
            System.out.println("Response Body: " +  response.body());

            return Boolean.parseBoolean( response.body());
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

    private static void produceOrder(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}