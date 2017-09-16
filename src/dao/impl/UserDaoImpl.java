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

import model.entity.OrderItem;
import model.entity.User;
import dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {


	@Autowired
	private JdbcTemplate db;
	
	public void add(final User entity) {
		final String sql = "INSERT INTO user (username,password,address, qq, phone) VALUES(?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
        db.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn)
                    throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, entity.getUsername());
                ps.setString(2, entity.getPassword());
                ps.setString(3, entity.getAddress());
                ps.setString(4, entity.getQq());
                ps.setString(5, entity.getPhone());
                return ps;
            }
        }, keyHolder);
        entity.setId(keyHolder.getKey().intValue());
	}

	public void remove(int id) {
		String sql = "DELETE FROM user WHERE id=?";
		db.update(sql, id);
	}

	public void update(User user) {
		String sql = "UPDATE user SET username=?, password=?, address=?, qq=?, phone=? WHERE id=?";
		db.update(sql, user.getUsername(), user.getPassword(), user.getAddress(), user.getQq(), user.getPhone(), user.getId());
	}

	public User find(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		return db.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));
	}

	public List<User> getAll() {
		String sql = "SELECT * FROM user";
		return db.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	public User findByUsername(String username) {
		String sql = "SELECT * FROM user WHERE username=?";
		return db.queryForObject(sql, new Object[]{username}, new BeanPropertyRowMapper<User>(User.class));
	}

	public User findUserByPhone(String phone) {
		String sql = "SELECT * FROM user WHERE phone=?";
		return db.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<User>(User.class));
	}

}
