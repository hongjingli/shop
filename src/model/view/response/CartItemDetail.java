package model.view.response;

import java.util.List;

import model.entity.CartItem;
import model.entity.Goods;

public class CartItemDetail {	
	private int cartItemId;		
	private int goodsId;
	private String name;
	private double singlePrice;
	private int quantity;
	private String mainImage;
	
	
	public CartItemDetail(CartItem item, Goods goods) {
		this.cartItemId = item.getId();
		this.goodsId = goods.getId();
		this.name = goods.getName();
		this.singlePrice = goods.getPrice();
		this.quantity = item.getQuantity();
		this.mainImage = goods.getMainImage();
	}
	
	public double getTotalPrice() {
		return singlePrice * quantity;
	}
	public CartItemDetail() {
		super();
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
	public int getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
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
		return "CartItemDetail [cartItemId=" + cartItemId + ", goodsId="
				+ goodsId + ", name=" + name + ", singlePrice=" + singlePrice
				+ ", quantity=" + quantity + ", mainImage=" + mainImage + "]";
	}
	
	
	
	
}
