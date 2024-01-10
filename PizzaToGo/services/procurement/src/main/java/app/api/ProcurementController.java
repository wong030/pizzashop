package app.api;

import app.api.dto.CreateIngredientsDto;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Path("/procurement")
public class ProcurementController {
    @Singleton

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doProcurment(CreateIngredientsDto createIngredientsDto) {
        try {
            boolean log = writeLog(createIngredientsDto);
            System.out.println("Write Log: " + log);
            return Response.ok().entity(log).build();

        } catch (Exception e) {

            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    private static String getTime() {

        LocalDateTime timeAndDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = timeAndDate.format(formatter);
        return formattedDateTime;
    }

    private static boolean writeLog(CreateIngredientsDto createIngredientsDto) {
        String [] ingredients = {
            Integer.toString(createIngredientsDto.getIngredient1Id()),
            Integer.toString(createIngredientsDto.getIngredient2Id()),
            Integer.toString(createIngredientsDto.getIngredient3Id()),
            Integer.toString(createIngredientsDto.getIngredient4Id()),
            Integer.toString(createIngredientsDto.getIngredient5Id()),
            Integer.toString(createIngredientsDto.getIngredient6Id()),
            Integer.toString(createIngredientsDto.getIngredient7Id()),
            Integer.toString(createIngredientsDto.getIngredient8Id())
        };
        String filePath = "procurementlog.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            
            for (String ingredient : ingredients) {
                bufferedWriter.write(ingredient + ", ");
            }
            bufferedWriter.write(getTime());
            bufferedWriter.write(";");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}