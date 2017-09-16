package model.entity;

import java.util.List;

public class Goods {
	private int id;
	private int categoryId;
	private int sellerId;
	private String name;
	private double price;
	private int quantity;
	private int status;
	private String mainImage;	//url
	private String description;
	
	public static int ON_SALE = 2;
	public static int OFF_SALE = 1;
	
	
	public Goods() {
		super();
		
	}
	public Goods(int categoryId, int sellerId, String name,
			double price, int quantity, int status, String mainImage,
			String description) {
		super();
		this.categoryId = categoryId;
		this.sellerId = sellerId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.mainImage = mainImage;
		this.description = description;
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
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", categoryId=" + categoryId + ", sellerId="
				+ sellerId + ", name=" + name + ", price=" + price
				+ ", quantity=" + quantity + ", status=" + status
				+ ", mainImage=" + mainImage + ", description=" + description
				+ "]";
	}
	
}
