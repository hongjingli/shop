package service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartItemDao;
import dao.CategoryDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.UserDao;
import exception.AuthException;
import exception.DuplicateException;
import model.entity.Admin;
import model.entity.CartItem;
import model.entity.Category;
import model.entity.Goods;
import model.entity.GoodsImage;
import model.entity.Order;
import model.entity.OrderItem;
import model.entity.User;
import model.view.request.PersonEditForm;
import model.view.request.UserLoginForm;
import model.view.request.UserRegisterForm;
import model.view.response.CartItemDetail;
import model.view.response.CartOutline;
import model.view.response.CategoryTree;
import model.view.response.GoodsDetail;
import model.view.response.GoodsPage;
import model.view.response.OrderDetail;
import model.view.response.OrderItemDetail;
import service.BuyerService;
import util.E;

@Service
public class BuyerServiceImpl implements BuyerService {

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

	public List<CartItemDetail> getCart(int userId) {
		List<CartItemDetail> list = new ArrayList<CartItemDetail>();
		List<CartItem> cart = cartItemDao.getUserCart(userId);
		for (CartItem item : cart) {
			Goods goods = goodsDao.find(item.getGoodsId());
			list.add(new CartItemDetail(item, goods));
		}
		return list;
	}

	public CartOutline getCartOutline(int userId) {
		double price = 0;
		int itemNum = 0;
		List<CartItemDetail> list = getCart(userId);
		for (CartItemDetail item : list) {
			price += item.getTotalPrice();
		}
		itemNum = list.size();
		return new CartOutline(price, itemNum);
	}

	public void removeCartItem(int cartItemId) {
		cartItemDao.remove(cartItemId);
	}

	public void addCartItem(int buyerId, int goodsId, int quantity) {
		List<CartItem> cart = cartItemDao.getUserCart(buyerId);
		for (CartItem i : cart) {
			if (i.getGoodsId() == goodsId) {
				i.setQuantity(i.getQuantity() + quantity);
				cartItemDao.update(i);
				return;
			}
		}
		
		CartItem item = new CartItem(buyerId, goodsId, quantity);
		cartItemDao.add(item);
	}

	
	
	public GoodsDetail getGoodsDetail(int goodsId) {
		return GoodsDetail.fromGoodsId(goodsId, goodsDao, categoryDao, userDao);
	}

	private int getPageNum(List<Goods> goods) {
		return (int) Math.ceil((double) goods.size() / GoodsPage.SIZE);
	}

	private GoodsPage getGoodsPage(List<Goods> goods, int currentPage) {
		int pageNum = getPageNum(goods);

		int minPage, maxPage;

		if (pageNum < currentPage + GoodsPage.PREVIEW_WIDTH) {
			maxPage = pageNum;
		} else {
			maxPage = currentPage + GoodsPage.PREVIEW_WIDTH;
		}

		if (currentPage - GoodsPage.PREVIEW_WIDTH < 1) {
			minPage = 1;
		} else {
			minPage = currentPage - GoodsPage.PREVIEW_WIDTH;
		}

		// 当前页的商品数量
		int currSize = goods.size() - (currentPage - 1) * GoodsPage.SIZE;
		if (currSize > GoodsPage.SIZE)
			currSize = GoodsPage.SIZE;

		return new GoodsPage(goods.subList(currentPage - 1, currentPage - 1
				+ currSize), currentPage, minPage, maxPage);
	}

	private List<Goods> filterOnSale(List<Goods> goodsList) {
		List<Goods> gl = new LinkedList<Goods>();
		for (Goods g : goodsList) {
			if (g.getStatus() == Goods.ON_SALE)
				gl.add(g);
		}
		return gl;
	}
	
	
	public GoodsPage getCategoryPage(int categoryId, int currentPage) {
		
		List<Category> cl = categoryDao.findChildAndSelf(categoryId, categoryId);
		
		List<Goods> goods = new LinkedList<Goods>();
		for (Category c : cl) {
			goods.addAll(goodsDao.findByCategoryId(c.getId()));
		}
		
		goods = filterOnSale(goods);

		return getGoodsPage(goods, currentPage);
	}

	public GoodsPage searchGoods(String keyWord, int currentPage) {
		List<Goods> goods = goodsDao.search(keyWord);

		goods = filterOnSale(goods);
		
		return getGoodsPage(goods, currentPage);
	}

	public List<Goods> getLatestGoods(int maxAmount) {
		List<Goods> goods = goodsDao.getLatestGoods(maxAmount);
		
		goods = filterOnSale(goods);
		
		return goods;
	}

	public List<OrderDetail> getOrderList(int buyerId) {
		List<Order> orders = orderDao.findOrderByBuyer(buyerId);
		
		return OrderDetail.fromOrderList(orders, orderDao, userDao, goodsDao);
	}

	public void makeOrder(int userId) {
		List<CartItem> cart = cartItemDao.getUserCart(userId);
		HashMap<Integer, Order> m = new HashMap<Integer, Order>();

		for (CartItem item : cart) {
			Goods goods = goodsDao.find(item.getGoodsId());
			Order order = m.get(goods.getSellerId());
			if (order == null) {
				order = new Order(userId, goods.getSellerId(), new Date(), 0);
				m.put(goods.getSellerId(), order);
				orderDao.add(order);
			}
			OrderItem oItem = new OrderItem(order.getId(), item.getGoodsId(),
					item.getQuantity(), goods.getPrice());
			orderDao.addItem(oItem);
			cartItemDao.remove(item.getId());
			goods.setQuantity(goods.getQuantity() - item.getQuantity());
			goodsDao.update(goods);
		}
	}

	public User login(UserLoginForm form) throws AuthException {
		User login = null;
		try {
			login = userDao.findByUsername(form.getUsername());
		} catch (Exception e) {
			throw new AuthException("用户名不存在");
		}
		if (!form.getPassword().equals(login.getPassword())) {
			throw new AuthException("密码错误");
		}
		return login;
	}

	private void checkPhone(String phone, User user) throws DuplicateException {		
		try {
			User findPhone = userDao.findUserByPhone(phone);			
		} catch (Exception e) {
			return;
		}		
		if(user !=null && user.getPhone().equals(phone))
			return;
		throw new DuplicateException("此手机号已被使用");
	}

	private void checkUsername(String username) throws DuplicateException {
		try {
			User findPhone = userDao.findByUsername(username);
		} catch (Exception e) {
			return;
		}
		throw new DuplicateException("此用户名已被使用");
	}

	public void editPerson(PersonEditForm form) throws DuplicateException {
		User user = userDao.find(form.getId());
		checkPhone(form.getPhone(), user);
		user.setAddress(form.getAddress());
		user.setPhone(form.getPhone());
		user.setQq(form.getQq());
		userDao.update(user);
	}

	public User register(UserRegisterForm form) throws DuplicateException {
		User user = new User();
		checkUsername(form.getUsername());
		checkPhone(form.getPhone(), null);
		user.setAddress(form.getAddress());
		user.setPassword(form.getPassword());
		user.setQq(form.getQq());
		user.setPhone(form.getPhone());
		user.setUsername(form.getUsername());
		userDao.add(user);
		return user;
	}

	public void resetPassword(int userId, String password) {
		User user = userDao.find(userId);
		user.setPassword(password);
		userDao.update(user);
	}

	public CategoryTree getCategoryTree() {
		List<Category> cl = categoryDao.getAll();
		CategoryTree pct = new CategoryTree();		
		for (Category c : cl) {
			if (c.isTop()) {
				CategoryTree cct = new CategoryTree(c);
				List<Category> cs = categoryDao.findChild(c.getId());
				for (Category e : cs) {
					cct.getChild().add(new CategoryTree(e));
				}
				pct.getChild().add(cct);
			}
		}
		return pct;
	}
	public List<Goods> getSameCategoryGoods(int categoryId, int maxAmount) {
		GoodsPage page = getCategoryPage(categoryId,1);
		if (maxAmount > page.getGoodsList().size())
			maxAmount = page.getGoodsList().size();
		return page.getGoodsList().subList(0, maxAmount);
	}
}
