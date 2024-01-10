package app.api.dto;

public class UserLoginResponseData {
	 private String Username;
	 private String Token;
	 private int userId;

	 public UserLoginResponseData(String username, String uuid, int userId) {
	     this.Username = username;
	     this.Token = uuid;
	     this.userId = userId;
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
    
    public int getUserId() {
    	return this.userId;
    }
    
    public void setUserId(int userId) {
    	this.userId = userId;
    }
}
