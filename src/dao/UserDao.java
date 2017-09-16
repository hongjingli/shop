package dao;

import java.util.List;

import model.entity.Admin;
import model.entity.User;

public interface UserDao extends BaseDao<User> {

	User findByUsername(String username) ;

	User findUserByPhone(String phone) ;
}
