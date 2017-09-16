package model.view.request;

public class UserLoginForm implements IFrom{
	private String username;
	private String password;
	
	public UserLoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserLoginForm() {
	}
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
			throw new InvalidFormException("不合法的用户名长度，用户名长度应为1~45");
		else if(password.length()>45||password.length()==0)
			throw new InvalidFormException("不合法的密码长度，密码长度应为1~45");
		
	}
	@Override
	public String toString() {
		return "UserLoginForm [username=" + username + ", password=" + password
				+ "]";
	}
	
}
