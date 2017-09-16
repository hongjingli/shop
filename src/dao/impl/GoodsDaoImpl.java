package dao.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.aspectj.apache.bcel.generic.F2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import util.E;
import model.entity.CartItem;
import model.entity.Goods;
import model.entity.GoodsImage;
import dao.GoodsDao;

@Repository
public class GoodsDaoImpl implements GoodsDao {

	@Autowired
	private JdbcTemplate db;
	
	public void add(final Goods entity) {
		final String sql = "INSERT INTO goods (category_id,seller_id,name, price, quantity, status, main_image, description) VALUES(?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getCategoryId());
                ps.setInt(2, entity.getSellerId());
                ps.setString(3, entity.getName());
                ps.setDouble(4, entity.getPrice());
                ps.setInt(5, entity.getQuantity());
                ps.setInt(6, entity.getStatus());
                ps.setString(7, entity.getMainImage());
                ps.setString(8, entity.getDescription());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		//删除记录
		String sql = "DELETE FROM goods WHERE id=?";
		db.update(sql, id);
		
		//删除图片
		removeAllMoreImages(id);
	}
	
	public void removeAllMoreImages(int goodsId) {
		List<GoodsImage> goodsImages = getMoreImage(goodsId);
		for (GoodsImage image : goodsImages) {
			File file = new File(image.getImageUrl());
			file.delete();
		}
		String sql = "DELETE FROM goods WHERE goods_id=?";
		db.update(sql, goodsId);
	}

	public void update(Goods entity) {
			String sql = "UPDATE goods SET category_id=?, seller_id=?, name=?, price=?, quantity=?, status=?, main_image=?, description=? WHERE id=?";
			db.update(sql, entity.getCategoryId(), entity.getSellerId(), entity.getName(), entity.getPrice(), entity.getQuantity(), entity.getStatus(), entity.getMainImage(), entity.getDescription(), entity.getId());
	}

	public Goods find(int id) {
		String sql = "SELECT * FROM goods WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public List<Goods> getAll() {
		String sql = "SELECT * FROM goods";
		return db.query(sql, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public List<GoodsImage> getMoreImage(int goodsId) {
		String sql = "SELECT * FROM goods_image WHERE goods_id = ?";
		return db.query(sql, new Object[]{goodsId},  new BeanPropertyRowMapper<GoodsImage>(GoodsImage.class));
	}

	public List<Goods> getLatestGoods(int maxAmount) {
		String sql = "SELECT * FROM goods ORDER BY id DESC limit 0,?";
		return db.query(sql, new Object[]{maxAmount}, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public List<Goods> findByCategoryId(int categoryId) {
		String sql = "SELECT * FROM goods WHERE category_id =?";
		return db.query(sql, new Object[]{categoryId}, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public List<Goods> search(String keyWord) {
		String keyPattern = "%" + keyWord + "%";
		String sql = "SELECT * FROM goods WHERE name like ?";
		return db.query(sql, new Object[]{keyPattern}, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public List<Goods> findSale(int sellerId) {
		String sql = "SELECT * FROM goods WHERE seller_id =? AND (status=1 OR status=2)";
		return db.query(sql, new Object[]{sellerId}, new BeanPropertyRowMapper<Goods>(Goods.class));
	}

	public void addMoreImage(final GoodsImage entity) {
		final String sql = "INSERT INTO goods_image (goods_id, image_url, order_no) values(?,?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getGoodsId());
                ps.setString(2, entity.getImageUrl());
                ps.setInt(3, entity.getOrderNo());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void lazyRemove(int goodsId) {
		String sql = "UPDATE goods SET status=3 WHERE id=?";
		db.update(sql, goodsId);
	}
	
	public void updateMoreImage(GoodsImage goodsImage) {
		String sql = "UPDATE goods_image SET image_url=?, goods_id=?, order_no=? WHERE id=?";
		db.update(sql, goodsImage.getImageUrl(), goodsImage.getGoodsId(), goodsImage.getOrderNo(), goodsImage.getId());
	}
}
