package app.api.dto;

public class UserLoginDto {
	private String Username;
	private String Password;

	public String getUsername() {
	    return Username;
	}

	public void setUsername(String username) {
	    Username = username;
	}

	public String getPassword() {
	    return Password;
	}

	public void setPassword(String password) {
	    Password = password;
	}
}