package app.api.dto;

public class UserLoginResponseData {
	 private String Username;
	 private String Token;

	 public UserLoginResponseData(String username, String uuid) {
	     this.Username = username;
	     this.Token = uuid;
	 }

	 public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
