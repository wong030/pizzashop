package app.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import app.model.User;
import app.dao.UserDAO;
import app.api.dto.UserDTO;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/user")
public class UserController {

    @GET
    @Path("/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String doSomething(@PathParam("parameter") String parameter) {
        return String.format("Processed parameter value '%s'", parameter);
    }
}
