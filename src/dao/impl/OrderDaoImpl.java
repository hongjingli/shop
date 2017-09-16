package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.entity.Goods;
import model.entity.Order;
import model.entity.OrderItem;
import dao.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao {


	@Autowired
	private JdbcTemplate db;
	
	public void add(final Order entity) {
		final String sql = "INSERT INTO `order` (buyer_id, seller_id, order_time, status) VALUES(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getBuyerId());
                ps.setInt(2, entity.getSellerId());
                ps.setString(3, entity.getOrderTime());
                ps.setInt(4, entity.getStatus());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		String sql = "DELETE FROM `order` WHERE id=?";
		db.update(sql, id);
	}

	public void update(Order entity) {
		String sql = "UPDATE `order` SET buyer_id=?, seller_id=?, order_time=?, status=? WHERE id=?";
		db.update(sql, entity.getBuyerId(), entity.getSellerId(), entity.getOrderTime(), entity.getStatus(), entity.getId());
	}

	public Order find(int id) {
		String sql = "SELECT * FROM `order` WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public List<Order> getAll() {
		String sql = "SELECT * FROM `order`";
		return db.query(sql, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public void removeItem(int orderItemId) {
		String sql = "DELETE FROM order_item WHERE id=?";
		db.update(sql, orderItemId);
	}

	public void addItem(final OrderItem entity) {
		final String sql = "INSERT INTO order_item (order_id,origin_goods_id,quantity, price) VALUES(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getOrderId());
                ps.setInt(2, entity.getOriginGoodsId());
                ps.setInt(3, entity.getQuantity());
                ps.setDouble(4, entity.getPrice());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void updateItem(OrderItem entity) {
		String sql = "UPDATE order_item SET order_id=?, origin_goods_id=?, quantity=?, price=? WHERE id=?";
		db.update(sql, entity.getOrderId(), entity.getOriginGoodsId(), entity.getQuantity(), entity.getPrice(), entity.getId());
	}

	public List<Order> findOrderBySeller(int sellerId) {
		String sql = "SELECT * FROM `order` WHERE seller_id = ?";
		return db.query(sql, new Object[]{sellerId}, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public List<Order> findOrderByBuyer(int buyerId) {
		String sql = "SELECT * FROM `order` WHERE buyer_id = ?";
		return db.query(sql, new Object[]{buyerId}, new BeanPropertyRowMapper<Order>(Order.class));
	}

	public List<OrderItem> findOrderItems(int orderId) {
		String sql = "SELECT * FROM `order_item` WHERE order_id = ?";
		return db.query(sql, new Object[]{orderId}, new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
	}

}
