import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeliveryController {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void processOrder(String jsonDelivery) {
        Gson gson = new Gson();
        Delivery delivery = gson.fromJson(jsonDelivery, Delivery.class);

        String orderID = delivery.getOrderID();
        String userID = delivery.getUserID();
        //Datenbankzugriff mit OrderID und UserID
        // was Sinnvol währe Adresse Preis Name Nachname Ankunft Zugestellt
        //String sql = "SELECT plz, location, street, houseNumber FROM user_address WHERE userID = '" + userID + "'";
        /* 
        try {
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
            request(MediaType.APPLICATION_JSON);
            String jsonResponse = response.toString();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String[] Userdata = jsonResponse.split(", ");  dann sollte der entstandene String aufgeteilt sein mit dem wissen übber die Reihenfolge könnte man sie dann auf die Variablen zuweisen z.B.:
        String plz = Userdat[0];
        String location = Userdat[1];
        String street = Userdat[2];
        String houseNumber = Userdat[3];
        */


        //oder

    /*
    try {
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

    // JSON-String parsen mit Gson
    Gson gson = new Gson();
    JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);

    // Werte aus dem JSON-Objekt extrahieren
    String plz = jsonObject.get("plz").getAsString();
    String location = jsonObject.get("location").getAsString();
    String street = jsonObject.get("street").getAsString();
    String houseNumber = jsonObject.get("houseNumber").getAsString();

    // Jetzt kannst du die Werte verwenden, z.B. ausgeben
    System.out.println("PLZ: " + plz);
    System.out.println("Location: " + location);
    System.out.println("Street: " + street);
    System.out.println("House Number: " + houseNumber);

    } catch (IOException e) {
    e.printStackTrace();
    } 
    */

        String plz = "76891";
        String location = "Bruchweiler-Bärenbach";
        String street = "Hauptstraße";
        String houseNumber = "12b";

        LocalDateTime timeAndDate = LocalDateTime.now();
        String output = timeAndDate.format(FORMATTER);

        writeLog(orderID + "," + output + "," + plz + "," + location + "," + street + "," + houseNumber+";");
    }

    private static void writeLog(String s) {
        String dataPath = "deliverylog.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataPath, true))) {
            bufferedWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}