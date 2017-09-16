package dao;

import java.util.List;

import model.entity.Admin;

public interface AdminDao extends BaseDao<Admin> {
	
	Admin findByUsername(String username); 
	
}
