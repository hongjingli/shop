package service;

import java.util.List;

import exception.AuthException;
import model.entity.Admin;
import model.entity.Category;
import model.entity.User;
import model.view.request.AdminLoginForm;
import model.view.request.CategoryEditForm;

public interface AdminService {
	
	Admin login(AdminLoginForm form) throws AuthException;
	
	List<Category> getCategoryList();
	
	void addCategory(CategoryEditForm form);
	
	void editCategory(CategoryEditForm form);
	
	void removeCategory(int categoryId);
	
	List<User> getUserList();
	
	void removeUser(int userId);
			
}
