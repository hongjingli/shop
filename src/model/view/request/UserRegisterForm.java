package model.view.request;

import util.E;

public class UserRegisterForm implements IFrom{
	private String username;
	private String password;
	private String address;
	private String qq;
	private String phone;
	
	public UserRegisterForm() {
		super();
		
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void validate() throws InvalidFormException {
		new E(this);
			if(username.length()>45||username.length()==0)
				throw new InvalidFormException("不合法的用户名，用户名长度应为1~45");
			else if(password.length()>45||password.length()==0)
				throw new InvalidFormException("不合法的密码长度，密码长度应为1~45");
			else if (phone.length()==0)
				throw new InvalidFormException("请输入正确的手机号");
			else if (address.length()>100)
				throw new InvalidFormException("地址长度太长");
			else if (qq.length()>15)
				throw new InvalidFormException("不合法的QQ号");
	}

	@Override
	public String toString() {
		return "UserRegisterForm [username=" + username + ", password="
				+ password + ", address=" + address + ", qq=" + qq + ", phone="
				+ phone + "]";
	}
	
}
