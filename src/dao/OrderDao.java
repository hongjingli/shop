package dao;

import java.util.List;

import model.entity.Order;
import model.entity.OrderItem;

public interface OrderDao extends BaseDao<Order> {
	
	List<Order> findOrderBySeller(int sellerId);
	
	List<Order> findOrderByBuyer(int buyerId);
	
	List<OrderItem> findOrderItems(int orderId);
	
	void removeItem(int orderItemId);
	
	void addItem(OrderItem item);
	
	void updateItem(OrderItem item);
}
