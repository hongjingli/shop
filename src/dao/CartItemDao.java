package dao;

import java.util.List;

import model.entity.CartItem;

public interface CartItemDao extends BaseDao<CartItem> {

	List<CartItem> getUserCart(int userId);
	
}
