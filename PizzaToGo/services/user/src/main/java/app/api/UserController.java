package app.api;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Singleton;

import app.model.User;
import app.dao.UserDAO;
import app.api.dto.RegistrationData;
import app.api.dto.UserResponseData;

@Path("user")
@Singleton
public class UserController {
	
	@Inject
	private UserDAO userDAO;


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
	@Path("{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@PathParam("userId") int userId) {
		final User user = userDAO.readUser(userId);

		final UserResponseData userData = UserResponseData.fromEntity(user);

		return Response.ok().entity(userData).build();
	}
	

	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkUsername(@PathParam("username") String username) {
		User user = null;
		try {
			user = userDAO.readUser(username);
		}
		catch(Exception ignored){

		}
		boolean isFree = user == null;
		return Response.ok().entity(isFree).build();
	}

	
   
}
