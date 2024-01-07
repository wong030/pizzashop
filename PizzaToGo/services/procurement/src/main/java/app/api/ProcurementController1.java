import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProcurementController {

    public static void processOrder(String jsonOrderlist) {
        Gson gson = new Gson();
        Procurement procurement = gson.fromJson(jsonOrderlist, Procurement.class);
        String[] ingredients = procurement.getIngredients();

        LocalDateTime timeAndDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = timeAndDate.format(formatter);

        writeLog(ingredients, formattedDateTime);
    }

    private static void writeLog(String[] ingredients, String formattedDateTime) {
        String filePath = "procurementlog.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            // Schreibe jedes Zutatentext getrennt durch ein Komma
            for (String ingredient : ingredients) {
                bufferedWriter.write(ingredient + ", ");
            }
            bufferedWriter.write(formattedDateTime);
            bufferedWriter.write(";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}