package app.api;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.inject.Inject;

import app.dao.UserDAO;

@Path("access")
@Singleton
public class AccessController {
	@Inject
	private UserDAO userDAO;
}
