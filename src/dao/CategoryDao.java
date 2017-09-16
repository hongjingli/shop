package dao;

import java.util.List;
import java.util.Map;

import model.entity.Category;

public interface CategoryDao extends BaseDao<Category> {

	List<Category> findChildAndSelf(int parentId, int id);
	
	List<Category> findChild(int parentId);
	
	Map<Integer, Category> getMap();
}
