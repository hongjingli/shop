package model.entity;

public class User {
	private int id;
	private String username;	
	private String password;
	private String address;
	private String qq;
	private String phone;
	
	public User() {
		super();
		
	}
	
	public User(String username, String password, String address,
			String qq, String phone) {
		super();
		this.username = username;
		this.password = password;
		this.address = address;
		this.qq = qq;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", address=" + address + ", qq=" + qq + ", phone="
				+ phone + "]";
	}
	
}
