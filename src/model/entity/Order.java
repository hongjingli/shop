package model.entity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.DateUtil;
import util.E;


public class Order {
	private int id;
	private int buyerId;
	private int sellerId;
	private Date orderTime;
	private int status;
	
	public static int BUYER_ORDERED = 0;
	public static int SUCCESS = 1;
	public static int SELLER_REJECT = 2;
	
	
	public Order() {
		super();
		
	}
	
	public Order(int buyerId, int sellerId, Date orderTime, int status) {
		super();
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.orderTime = orderTime;
		this.status = status;
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
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getOrderTime() {
		return DateUtil.format(orderTime);
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = DateUtil.parse(orderTime);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", buyerId=" + buyerId + ", sellerId="
				+ sellerId + ", orderTime=" + orderTime + ", status=" + status
				+ "]";
	}
}
