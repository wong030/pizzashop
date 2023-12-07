package app.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
public class AuthenticationService {
    
    public Boolean authenticateUser(int userID) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();

        // Map für die Benutzerdaten
        Map<String, Object> userData = new HashMap<>();
        userData.put("userID", userID);
        

        // Konvertieren in JSON-String
        String jsonBody = toJsonString(userData);

        URI uri = URI.create("http://localhost:9082/authenticate");

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
