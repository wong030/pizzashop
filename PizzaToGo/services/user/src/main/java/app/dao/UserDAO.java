package app.dao;

import app.model.User;
import app.api.dto.RegistrationData;

public interface UserDAO {
	
	User createUser(RegistrationData data);
	
	User readUser(int id);
	
	User readUser(String username);
}
