package app.api;

import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import app.model.User;
import app.model.UserManager;
import app.dao.UserDAO;
import app.api.dto.RegistrationData;
import app.api.dto.UserResponseData;
import app.api.security.AccessManager;

@Path("user")
@Singleton
public class UserController {
	
	@Inject
	private UserDAO userDAO;
	
	@Inject
	private UserManager userManager;
	
	@Inject
	private AccessManager accessManager;



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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response getUser(@HeaderParam("token") String loginToken) {

		// Access-controll
		UUID uuid = UUID.fromString(loginToken);
		if (this.accessManager.isLoggedIn(uuid) == false) {
			System.out.println("ERROR Access not allowed");
			return Response.status(404, "Not logged in").build();
		}

		Optional<String> optUsername = accessManager.getLoginName(UUID.fromString(loginToken));

		if (optUsername.isPresent()) {
			Optional<User> optUser = userManager.lookupUser(optUsername.get());

			if (optUser.isPresent()) {
				User user = optUser.get();
				UserResponseData userResponse = UserResponseData.fromEntity(user);
				
				return Response.ok().entity(userResponse).build();
			} else {
				return Response.status(404).build();
			}
		} else {
			return Response.status(404).build();
		}
	}
	
   
}
