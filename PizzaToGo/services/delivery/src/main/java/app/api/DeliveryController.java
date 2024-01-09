package app.api;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/delivery")
public class DeliveryController {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doDelivery(@PathParam("parameter") String gsonString) {
        processOrder(gsonString);
        return gsonString; 
    }

    private void processOrder(String gsonString) {
        Production pizzaOrder = ProductionController.fromJsonString(gsonString);

        String[] ingredients = pizzaOrder.getIngredients();
        String orderID = pizzaOrder.getOrderID();
        String userID = pizzaOrder.getUserID();

        String apiUrl = "http://localhost:9082/api/user/" + userId;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();
    
        
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
    
        
        String plz = jsonObject.get("plz").getAsString();
        String location = jsonObject.get("location").getAsString();
        String street = jsonObject.get("street").getAsString();
        String houseNumber = jsonObject.get("houseNumber").getAsString();

        LocalDateTime timeAndDate = LocalDateTime.now();
        String output = timeAndDate.format(FORMATTER);

      
        String logEntry = orderID + "," + output + "," + plz + "," + location + "," + street + "," + houseNumber + ";";
        writeLog(logEntry);
    }

    private void writeLog(String s) {
        String dataPath = "deliverylog.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath, true))) {
            bufferedWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}