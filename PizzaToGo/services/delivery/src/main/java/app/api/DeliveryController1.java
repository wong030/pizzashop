import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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