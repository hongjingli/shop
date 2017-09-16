package model.entity;

public class OrderItem {	
	private int id;
	private int orderId;
	private int originGoodsId;
	private int quantity;
	private double price;	//单价
	
	public OrderItem() {
		super();
		
	}
	public OrderItem(int orderId, int originGoodsId, int quantity,
			double price) {
		super();
		this.orderId = orderId;
		this.originGoodsId = originGoodsId;
		this.quantity = quantity;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOriginGoodsId() {
		return originGoodsId;
	}
	public void setOriginGoodsId(int originGoodsId) {
		this.originGoodsId = originGoodsId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId
				+ ", originGoodsId=" + originGoodsId + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}
