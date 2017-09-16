package model.view.response;

import model.entity.CartItem;
import model.entity.Goods;
import model.entity.OrderItem;

public class OrderItemDetail {
	private int goodsId;
	private String name;
	private double singlePrice;
	private int quantity;
	private String mainImage;
	
	public OrderItemDetail(OrderItem item, Goods goods) {
		this.goodsId = goods.getId();
		this.name = goods.getName();
		this.singlePrice = goods.getPrice();
		this.quantity = item.getQuantity();
		this.mainImage = goods.getMainImage();
	}
	
	public OrderItemDetail() {
		super();
		
	}

	public double getTotalPrice() {
		return singlePrice * quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(double singlePrice) {
		this.singlePrice = singlePrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Override
	public String toString() {
		return "OrderItemDetail [goodsId=" + goodsId + ", name=" + name
				+ ", singlePrice=" + singlePrice + ", quantity=" + quantity
				+ ", mainImage=" + mainImage + "]";
	}
}
