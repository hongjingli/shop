package model.entity;

public class CartItem {
	private int id;
	private int buyerId;
	private int goodsId;
	private int quantity;
	
	
	public CartItem() {
		super();
	}
	public CartItem(int buyerId, int goodsId, int quantity) {
		super();
		this.buyerId = buyerId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", buyerId=" + buyerId + ", goodsId="
				+ goodsId + ", quantity=" + quantity + "]";
	}
}
