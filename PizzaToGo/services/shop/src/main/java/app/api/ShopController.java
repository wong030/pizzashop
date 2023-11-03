package app.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/shop")
public class ShopController {

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doSomething(@PathParam("parameter") String parameter) {
        return String.format("Processed parameter value '%s'", parameter);
    }
}
