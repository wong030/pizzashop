package app.api;

import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Arrays;
import java.util.UUID;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import app.api.dto.UserLoginDto;
import app.api.dto.UserLoginResponseData;
import app.api.security.AccessManager;
import app.dao.UserDAO;
import app.model.User;
import app.util.PasswordHelper;

@Path("access")
@Singleton
public class AccessController {
	
	@Inject	
	private AccessManager accessManager;

	@Inject
	private UserDAO userDAO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public Response login(UserLoginDto userLoginDto) {
		String username = userLoginDto.getUsername();
		String password = userLoginDto.getPassword();
		
		byte[] passwordHash = null;
		User user = null;
		try {
			user = userDAO.readUser(username);
			passwordHash = PasswordHelper.generatePasswordHash(password, user.getPasswordSalt());
			if(Arrays.equals(passwordHash, user.getPasswordHash())) {
				String token = accessManager.login(username).toString();
				return Response.ok().entity(new UserLoginResponseData(username, token)).build();
			} else {
				return Response.status(401,"wrong username or password").build();
			}
		} catch(NoResultException ex) {
			System.err.println(ex.toString());
			return Response.ok(401,"wrong username or password").build();
		}
		catch (RuntimeException ex) {
            System.err.println(ex.toString());
            return Response.status(409, "user is already logged in").build();
        } 
		catch (Exception ex){
            System.err.println(ex.toString());
            return Response.serverError().build();
        }
    }
	
	@DELETE
	public Response logout(@HeaderParam("token") String token) {
		try {
			accessManager.logout(UUID.fromString(token));
			return Response.ok().build();
		} catch(Exception ex) {
			return Response.serverError().build();
		}
	}
		
		
}

