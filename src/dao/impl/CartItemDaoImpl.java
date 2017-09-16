package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.entity.CartItem;
import model.entity.Category;
import dao.CartItemDao;

@Repository
public class CartItemDaoImpl implements CartItemDao {

	@Autowired
	private JdbcTemplate db;
	
	public void add(final CartItem entity) {
		final String sql = "INSERT INTO cart_item (buyer_id, goods_id, quantity) VALUES(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getBuyerId());
                ps.setInt(2, entity.getGoodsId());
                ps.setInt(3, entity.getQuantity());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		String sql = "DELETE FROM cart_item WHERE id=?";
		db.update(sql, id);
	}

	public void update(CartItem cartItem) {
		String sql = "UPDATE cart_item SET buyer_id=?, goods_id=?, quantity=? WHERE id=?";
		db.update(sql, cartItem.getBuyerId(), cartItem.getGoodsId(), cartItem.getQuantity(), cartItem.getId());
	}

	public CartItem find(int id) {
		String sql = "SELECT * FROM cart_item WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<CartItem>(CartItem.class));
	}

	public List<CartItem> getAll() {
		String sql = "SELECT * FROM cart_item";
		return db.query(sql, new BeanPropertyRowMapper<CartItem>(CartItem.class));	
	}

	public List<CartItem> getUserCart(int userId) {
		String sql = "SELECT * FROM cart_item WHERE buyer_id=?";
		return db.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<CartItem>(CartItem.class));
	}

}
