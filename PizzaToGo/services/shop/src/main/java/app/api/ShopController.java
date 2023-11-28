package app.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.services.AuthenticationService;

@Path("/shop")
public class ShopController {

    @Inject
    private AuthenticationService authenticationService;

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doSomething(@PathParam("parameter") String parameter) {
        return String.format("Processed parameter value '%s'", parameter);
    }

    @GET
    @Path("/valid")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean validateAuthentication(String username, String password) throws IOException, InterruptedException{
        
        boolean response = authenticationService.authenticateUser(username, password);
        return response;
    }


    /* 
    @Path("/order")
    public String placeOrder(){

        return "x";
    }


    public String configurePizza(){


    }
    */
}
