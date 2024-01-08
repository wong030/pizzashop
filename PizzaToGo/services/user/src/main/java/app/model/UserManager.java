package app.model;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.api.dto.RegistrationData;
import app.api.dto.UserLoginDto;
import app.api.dto.UserResponseData;
import app.dao.UserDAO;

@Singleton
public class UserManager
{  
   
	@Inject
	private UserDAO userDAO;

	
	private Map<String, User> users = new ConcurrentHashMap<>();
   
   public UserManager()
   {
      super();
      
     
   }
   
   public Optional<User> lookupUser(String username)
   {
	   User user = userDAO.readUser(username);
	   return Optional.of(user);
   }
   
   public Optional<User> lookupUser(int userId)
   {
	   User user = userDAO.readUser(userId);
	   return Optional.of(user);
   }
   
   
   
   
   public Optional<User> register(RegistrationData registrationData)
   {
	   final User createdUser = userDAO.createUser(registrationData);
	   return Optional.of(createdUser);
   }
}
