package model.view.request;

import java.util.List;

import model.entity.Goods;
import model.entity.GoodsImage;

public class GoodsEditForm implements IFrom{
	private int id;
	private int categoryId;
	private String name;
	private double price;
	private int quantity;
	private int status;
	private String description;

	public Goods toGoods(String mainImage, int sellerId) {
		Goods g = new Goods(categoryId, sellerId, name, price, quantity, status, mainImage, description);
		g.setId(id);
		return g;
	}
	
	public void validate() throws InvalidFormException {
		java.text.DecimalFormat df =new java.text.DecimalFormat("########.00");
		if(name.length()>45||name.length()==0)
			throw new InvalidFormException("不合法的商品名称长度，商品名称长度应为1~45");
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}







	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	

}
