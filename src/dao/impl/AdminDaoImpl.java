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

import model.entity.Admin;
import dao.AdminDao;

@Repository
public class AdminDaoImpl implements AdminDao {

	@Autowired
	private JdbcTemplate db;
	
	public void add(final Admin entity) {
		final String sql = "INSERT INTO admin (username, password) VALUES(?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, entity.getUsername());
                ps.setString(2, entity.getPassword());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		String sql = "DELETE FROM admin WHERE id=?";
		db.update(sql, id);
	}

	public void update(Admin entity) {
		String sql = "UPDATE admin SET username=?, password=? WHERE id=?";
		db.update(sql, entity.getUsername(), entity.getPassword(), entity.getId());
	}

	public Admin find(int id) {
		String sql = "SELECT * FROM admin WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Admin>(Admin.class));	
	}

	public List<Admin> getAll() {
		String sql = "SELECT * FROM admin";
		return db.query(sql, new BeanPropertyRowMapper<Admin>(Admin.class));	
	}

	public Admin findByUsername(String username) {
		String sql = "SELECT * FROM admin WHERE username=?";
		return db.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<Admin>(Admin.class));	
	}

}
