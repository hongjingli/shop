package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDao;
import dao.CartItemDao;
import dao.CategoryDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.UserDao;
import exception.AuthException;
import model.entity.Admin;
import model.entity.Category;
import model.entity.User;
import model.view.request.AdminLoginForm;
import model.view.request.CategoryEditForm;
import service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private AdminDao adminDao;
	
	public Admin login(AdminLoginForm form) throws AuthException {
		Admin login = null;
		try {
			login = adminDao.findByUsername(form.getUsername());			
		} catch (Exception e) {
			throw new AuthException("用户名不存在");
		}
		if (form.getPassword() == login.getPassword()) {
			throw new AuthException("密码错误");
		} 		
		return login;
	}

	public List<Category> getCategoryList() {
		return categoryDao.getAll();
	}

	public void addCategory(CategoryEditForm form) {
		Category c = new Category(form.getParentId(), form.getName());
		categoryDao.add(c);
	}

	public void editCategory(CategoryEditForm form) {
		Category c = categoryDao.find(form.getId());
		c.setName(form.getName());
		c.setParentId(form.getParentId());
		categoryDao.update(c);
	}

	public void removeCategory(int categoryId) {
		categoryDao.remove(categoryId);
	}

	public List<User> getUserList() {
		return userDao.getAll();
	}

	public void removeUser(int userId) {
		userDao.remove(userId);
	}

}
