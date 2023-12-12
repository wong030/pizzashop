package app.dao;

import app.model.User;

import app.api.dto.RegistrationData;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import app.util.PasswordHelper;

@Named
@Model
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext(name = "jpa-unit")
	private EntityManager em;

	@Override
	@Transactional
	public User createUser(RegistrationData data) {
		User user = new User();
		user.setCity(data.getCity());
		user.setEmail(data.getEmail());
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setStreet(data.getStreet());
		user.setStreetNr(data.getStreetNr());
		user.setUserName(data.getUserName());
		user.setZip(data.getZip());

		byte[] salt;
		byte[] passwordHash;
		try {
			salt = PasswordHelper.generateSalt();
			passwordHash = PasswordHelper.generatePasswordHash(data.getPassword(), salt);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR User creation failed: " + e.getMessage());
		}

		user.setPasswordSalt(salt);
		user.setPasswordHash(passwordHash);

		em.persist(user);
		em.flush();
		em.refresh(user);

		return user;
	}

	
	

}
