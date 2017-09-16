package model.view.request;

public class AdminLoginForm implements IFrom {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void validate() throws InvalidFormException {

		if(username.length()>45||username.length()==0)
			throw new InvalidFormException("不合法的用户名，用户名长度应为1~45");
		else if(password.length()>45||password.length()==0)
			throw new InvalidFormException("不合法的密码，密码长度应为1~45");

	}
}
