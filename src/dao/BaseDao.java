package dao;

import java.util.List;

import model.entity.User;

public interface BaseDao<T> {	//参数和返回值为对应表实体（entity），使用时强制转换
	/**
	 * 
	 * @param 参数entity相应id会在插入后被修改
	 */
	void add(T entity);
	
	void remove(int id);
	
	void update(T entity);
	
	T find(int id);
	
	List<T> getAll();
}
