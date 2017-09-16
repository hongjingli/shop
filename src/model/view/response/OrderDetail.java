package model.view.response;

import java.util.LinkedList;
import java.util.List;

import dao.GoodsDao;
import dao.OrderDao;
import dao.UserDao;
import model.entity.Goods;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.User;

public class OrderDetail {
	private int id;
	private User seller;
	private User buyer;	
	private String orderTime;
	private int status;
	private List<OrderItemDetail> items;
	
	public static List<OrderDetail> fromOrderList(List<Order> orders, OrderDao orderDao, UserDao userDao, GoodsDao goodsDao) {
		List<OrderDetail> list = new LinkedList<OrderDetail>();
		for (Order order : orders) {
			List<OrderItem> items = orderDao.findOrderItems(order.getId());

			User buyer = userDao.find(order.getBuyerId());

			User seller = userDao.find(order.getSellerId());

			List<OrderItemDetail> itemDetailList = new LinkedList<OrderItemDetail>();
			for (OrderItem item : items) {
				Goods goods = goodsDao.find(item.getOriginGoodsId());
				OrderItemDetail oid = new OrderItemDetail(item, goods);
				itemDetailList.add(oid);
			}

			OrderDetail od = new OrderDetail(order, buyer, seller,
					itemDetailList);
			list.add(od);
		}
		return list;
	}
	
	public OrderDetail(Order order, User buyer, User seller, List<OrderItemDetail> items) {
		this.id = order.getId();
		this.buyer = buyer;
		this.seller = seller;
		this.orderTime = order.getOrderTime();
		this.status = order.getStatus();
		this.items = items;
	}
	
	public double getTotalPrice() {
		double price = 0;
		for (OrderItemDetail i : items) {
			price += i.getTotalPrice();
		}
		return price;
	}
	
	public OrderDetail() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<OrderItemDetail> getItems() {
		return items;
	}
	public void setItems(List<OrderItemDetail> items) {
		this.items = items;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", seller=" + seller + ", buyer="
				+ buyer + ", orderTime=" + orderTime + ", status=" + status
				+ ", items=" + items + "]";
	}
	
}
