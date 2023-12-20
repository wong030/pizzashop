import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Beschaffungswesen {

    public static void processOrder(String jsonOrderlist) {

        Gson gson = new Gson();
        Orderlist orderlist = gson.fromJson(jsonOrderlist, Orderlist.class);
        String[] ingredients = orderlist.getIngredients();

        LocalDateTime TimeAndDate = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String ausgabe = TimeAndDate.format(form);


        writeLog(ingredients+" "+ausgabe);
    }
   public static void writeLog(String s){
    String dateiPfad = "ausgabe.txt";
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dateiPfad))) {
        bufferedWriter.write(s);
    } catch (IOException e) {
        e.printStackTrace();
    }
   }
}