package app.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import app.model.Order;

@Singleton
public class ProductionService {

    public Boolean notified(Order order) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Map für die Benutzerdaten
        Map<String, Object> orderdto = new HashMap<>();
        orderdto.put("orderID", order.getOrderId());
        orderdto.put("date", currentDateTime.toLocalDate().toString());
        orderdto.put("time", currentDateTime.toLocalTime().toString());
        orderdto.put("userID", order.getUserId());
        orderdto.put("pizza", order.getPizza());
        orderdto.put("price", order.getPrice());
        orderdto.put("size", order.getSize());
        orderdto.put("ingredient1_id", order.getIngredient1Id());
        orderdto.put("ingredient2_id", order.getIngredient2Id());
        orderdto.put("ingredient3_id", order.getIngredient3Id());
        orderdto.put("ingredient4_id", order.getIngredient4Id());
        orderdto.put("ingredient5_id", order.getIngredient5Id());
        orderdto.put("ingredient6_id", order.getIngredient6Id());
        orderdto.put("ingredient7_id", order.getIngredient7Id());
        orderdto.put("ingredient8_id", order.getIngredient8Id());

        // Konvertieren in JSON-String
        String jsonBody = toJsonString(orderdto);

        URI uri = URI.create("http://localhost:9083/processOrder");

        // HTTP-POST-Request mit JSON-Body
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // Send Request und erhalte Response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        // Response-Body als boolean-Wert
        return Boolean.parseBoolean(response.body());
    }

    // Hilfsmethode
    private String toJsonString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Fehler beim Konvertieren in JSON", e);
        }
    }
}
