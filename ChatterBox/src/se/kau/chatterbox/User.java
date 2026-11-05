package se.kau.chatterbox;

public class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		if(username.length() >= 5)
			this.username = username;
		else
			System.out.println("Username must have atleast 5 letters");
	}
	public void setPassword(String password) {
		if(password.length() > 7)
			this.password = password;
		else
			System.out.println("Password must have atleast 8 letters");
	}
}
