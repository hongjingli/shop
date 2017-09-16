package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import model.entity.Category;
import dao.CategoryDao;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private JdbcTemplate db;
	
	public void add(final Category entity) {
		final String sql = "INSERT INTO category (parent_id, name) VALUES(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, entity.getParentId());
                ps.setString(2, entity.getName());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		String sql = "DELETE FROM category WHERE id=?";
		db.update(sql, id);
	}

	public void update(Category category) {
		String sql = "UPDATE category SET parent_id=?, name=? WHERE id=?";
		db.update(sql, category.getParentId(), category.getName(), category.getId());
	}

	public Category find(int id) {
		String sql = "SELECT * FROM category WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Category>(Category.class));
	}

	public List<Category> getAll() {
		String sql = "SELECT * FROM category";
		return db.query(sql, new BeanPropertyRowMapper<Category>(Category.class));	
	}

	public List<Category> findByParentId(int parentId) {
		String sql = "SELECT * FROM category WHERE parent_id=?";
		return db.query(sql, new Object[]{parentId}, new BeanPropertyRowMapper<Category>(Category.class));
	}

	public List<Category> findChild(int id) {
		String sql = "SELECT * FROM category WHERE parent_id=? AND id<>?";
		return db.query(sql, new Object[]{id, id}, new BeanPropertyRowMapper<Category>(Category.class));
	}
	public List<Category> findChildAndSelf(int parentId, int id) {
		String sql = "SELECT * FROM category WHERE parent_id=? OR id=?";
		return db.query(sql, new Object[]{parentId,id}, new BeanPropertyRowMapper<Category>(Category.class));
	}

	public Map<Integer, Category> getMap() {
		Map<Integer, Category> m = new HashMap<Integer, Category>();
		List<Category> all = getAll();
		for (Category a : all) {
			m.put(a.getId(), a);
		}
		return m;
	}
}
