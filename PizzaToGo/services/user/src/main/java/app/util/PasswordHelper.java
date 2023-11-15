package app.util;

//Imports für die Passwort Verschlüsselung
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordHelper {
	
	//this String defines which algorithm is used to hash the password (PBKDF2 Algorithm with HMAC-SHA-1 as pseudorandom function)
	private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA1";
	//length of the key
	private static final int KEY_LENGTH_BITS = 160;
	//number of iterations for the Hash-Algorithm
	private static final int ITERATION_COUNT = 65536;
	
	// pseudorandom number generator
	private static final String RANDOM_NUMBER_GENERATOR_ALGORITHM = "SHA1PRNG";
	//length of the password-salt
	private static final int SALT_LENGTH_BYTES = 32;

	
	//function to generate the password-hash.
	public static byte[] generatePasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
		final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
	    final KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH_BITS);

	    final SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

	    return secretKey.getEncoded();
	  }
	
	//function to generate the password-salt
	public static byte[] generateSalt() throws NoSuchAlgorithmException{
	    final SecureRandom random = SecureRandom.getInstance(RANDOM_NUMBER_GENERATOR_ALGORITHM);
	    final byte[] salt = new byte[SALT_LENGTH_BYTES];
	    random.nextBytes(salt);
	    return salt;
	  }

}
