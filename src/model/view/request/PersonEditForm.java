package model.view.request;

import model.entity.User;

public class PersonEditForm implements IFrom{
	private int id;	
	//只允许修改以下字段
	private String address;
	private String qq;
	private String phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		if(id==0)
		throw new InvalidFormException("用户id不能为空");
		//else if(username.length()>45||username.length()==0)
			//throw new InvalidFormException("不合法的用户名，用户名长度应为1~45");
		//else if(password.length()>45||password.length()==0)
			//throw new InvalidFormException("不合法的密码长度，密码长度应为1~45");
		else if (address.length()>100)
			throw new InvalidFormException("地址长度太长");
		else if (qq.length()>15)
			throw new InvalidFormException("不合法的QQ号");
	}

}
