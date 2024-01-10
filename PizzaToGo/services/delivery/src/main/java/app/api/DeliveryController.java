package app.api;

import app.api.dto.CreateOrderDTO;
import app.dao.OrderDAO;
import app.model.Order;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/delivery")
@Singleton
public class DeliveryController {
  
    @Inject private OrderDAO orderDAO;

  

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doDelivery(CreateOrderDTO createData) {
            try {
            final Order createdOrder = orderDAO.createOrder(createData);
            boolean log = writeLog(getUserInfo(createdOrder));
            System.out.println("Write Log: " + log);
            return Response.ok().entity(log).build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.serverError().build();

        }
    }

    private String getUserInfo(Order order){

        String userId =  Integer.toString(order.getUserId());
        String orderID = Integer.toString(order.getOrderId());
        String returnString = userId+", "+orderID;

        //String apiUrl = "http://localhost:9082/api/user/" +userId ;


        return returnString;
    }
/* 
 private void processOrder(createData) {
     String userId =  createData.getUserId();
     String orderID =  createData.getOrderId();

    String apiUrl = "http://localhost:9082/api/user/" +userId ;

    try {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Überprüfen des HTTP-Statuscodes
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

            String plz = jsonObject.get("plz").getAsString();
            String location = jsonObject.get("location").getAsString();
            String street = jsonObject.get("street").getAsString();
            String houseNumber = jsonObject.get("houseNumber").getAsString();

            LocalDateTime timeAndDate = LocalDateTime.now();
            String output = timeAndDate.format(FORMATTER);

            String logEntry = orderID + "," + output + "," + plz + "," + location + "," + street + "," + houseNumber + ";";
            writeLog(logEntry);
        } else {
            System.err.println("HTTP-Statuscode nicht OK. Response Code: " + responseCode);
        }

        connection.disconnect();
    } catch (IOException e) {
        e.printStackTrace();
    }
 }*/
      private boolean writeLog(String s) {
        String output = s +", "+ getTime();
        String dataPath = "deliverylog.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath, true))) {
            bufferedWriter.write(output);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        private static String getTime() {

        LocalDateTime timeAndDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = timeAndDate.format(formatter);
        return formattedDateTime;
    }
}