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

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/procurement")
public class ProcurementController {

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doProcurment(@PathParam("parameter") String parameter) {
        
        processOrder(parameter);
        return String.format("Processed parameter value '%s'", parameter);
    }

    private static void processOrder(String jsonOrderlist) {
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