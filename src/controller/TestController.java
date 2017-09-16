package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import model.entity.Admin;
import model.entity.CartItem;
import model.entity.Category;
import model.entity.Goods;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.User;
import model.view.response.CartItemDetail;
import model.view.response.JsonResult;

import org.apache.commons.collections.bag.TreeBag;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import dao.AdminDao;
import dao.BaseDao;
import dao.CartItemDao;
import dao.CategoryDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.UserDao;
import exception.AuthException;
import service.AdminService;
import util.DateUtil;
import util.E;

@Controller
public class TestController {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private AdminService adminService;
	
	@ResponseBody
	@RequestMapping("/submit")
	public String submit(MultipartHttpServletRequest request) {
		return "Get the datas!";
	}	
//	@RequestMapping("/test")
	public String test(ModelMap map) {

//		User user = userDao.find(4);
//		user.setPhone("13336411441");
//		userDao.update(user);
//		new E(user);
//		userDao.remove(user.getId());
//		new E(userDao.getAll());
		
//		
//		CartItem i = cartItemDao.find(4);
//		i.setQuantity(1000);
//		cartItemDao.update(i);
//		new E(i);
//		cartItemDao.remove(i.getId());
		
//		Goods g = goodsDao.find(4);
//		g.setName("strange");
//		goodsDao.update(g);
//		new E(g);
//		goodsDao.remove(g.getId());
		
		return "test";

	}
		
	@RequestMapping("/test")
	public String get() {
		return "test";
	}
	
	public static void main(String args[]) {
		
	}
}
