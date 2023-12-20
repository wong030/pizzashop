import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
public class Auslieferungslogistik {

    public static void processOrder(String jsonAuslieferung) {

        Gson gson = new Gson();
        Auslieferung auslieferung = gson.fromJson(jsonAuslieferung, Auslieferung.class);

        String orderID = auslieferung.getOrderID();
        String userID = auslieferung.getUserID();
        
        //Datenbankzugriff mit OrderID und UserID
        // was Sinnvol währe Adresse Preis Name Nachname Ankunft Zugestellt
        String Plz = "76891";
        String Ort = "Bruchweiler-Bärenbach";
        String Straße = "Hauptstraße";
        String HausNummer = "12b";

        LocalDateTime TimeAndDate = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ausgabe = TimeAndDate.format(form);

        writeLog(orderID+" "+ausgabe+" "+Plz+" "+Ort+" "+Straße+" "+HausNummer);
    }
    public static void writeLog(String s){
        String dateiPfad = "ausgabe2.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dateiPfad))) {
            bufferedWriter.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
}   