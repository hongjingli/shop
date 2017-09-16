package model.view.response;

import model.entity.User;

public class LoginResult extends JsonResult {

	public LoginResult(User user) {
		super(true, "登陆成功");		
	}
	
	public LoginResult(boolean b, String message) {
		super(b, message);
	}

	public LoginResult(String username, String userkey) {
		super(true);
		this.username = username;
		this.userkey = userkey;
	}

	private String username;
	private String userkey;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}
}
