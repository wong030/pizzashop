package app.api;

import java.util.Optional;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Singleton;

import app.model.User;
import app.model.UserManager;
import app.dao.UserDAO;
import app.api.dto.RegistrationData;
import app.api.dto.UserResponseData;

@Path("user")
@Singleton
public class UserController {
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UserManager userManager;



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(RegistrationData registrationData) {
		
		System.out.println();
		System.out.println("Register " + registrationData );
		
		final User createdUser = userDAO.createUser(registrationData);
		
	return Response.ok().entity(createdUser).build();
	}
	

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userId}")
	public Response getUserById(@PathParam("userId") int userId) {
		final Optional<User> userById = userManager.lookupUser(userId);
		if(userById.isPresent()) {
			User user = userById.get();
			UserResponseData userResponse = UserResponseData.fromEntity(user);
			
			return Response.ok().entity(userResponse).build();
		}else {
			return Response.status(404).build();
		}
	}
	
	
   
}
