package controller;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Admin;
import model.entity.CartItem;
import model.entity.Category;
import model.entity.Goods;
import model.entity.User;
import model.view.request.AdminLoginForm;
import model.view.request.CategoryEditForm;
import model.view.request.GoodsEditForm;
import model.view.request.InvalidFormException;
import model.view.request.PersonEditForm;
import model.view.request.UserLoginForm;
import model.view.request.UserRegisterForm;
import model.view.response.CartItemDetail;
import model.view.response.CartOutline;
import model.view.response.CommonVar;
import model.view.response.GoodsDetail;
import model.view.response.GoodsPage;
import model.view.response.JsonResult;
import model.view.response.LoginResult;
import model.view.response.OrderDetail;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CategoryDao;
import dao.impl.CategoryDaoImpl;
import exception.AuthException;
import exception.DuplicateException;
import util.Base64Util;
import util.CookieUtil;
import util.E;
import service.*;
import service.impl.BuyerServiceImpl;

@Controller
public class BuyerController {

	private User sessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("User");
	}

	private void setCookie(HttpServletResponse response, User user) {
		CookieUtil.add(response, "username", user.getUsername());
		CookieUtil.add(response, "userkey",
				Base64Util.encode(user.getPassword()));
	}

	private User getCookie(HttpServletRequest request) {
		String username = CookieUtil.get(request, "username");
		String password = CookieUtil.get(request, "userkey");
		if (username == null || password == null)
			return null;
		password = Base64Util.decode(password);

		try {
			return buyer.login(new UserLoginForm(username, password));
		} catch (AuthException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * session 中user为null则找cookie，都为空则要求重新登录
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkLogin(HttpServletRequest request) {
		if (request.getSession().getAttribute("User") != null)
			return true;
		User user = getCookie(request);
		if (user == null)
			return false;
		request.getSession().setAttribute("User", user);
		return true;
	}

	@Autowired
	private BuyerService buyer;

	@Autowired
	private CategoryDaoImpl categoryDao;

	
	private void commonPage(ModelMap map, int navActive) {
		map.put("com", new CommonVar(buyer.getCategoryTree(), navActive));		
	}
	@RequestMapping("/buyer/person/detail")
	public String person_detail(HttpSession session, ModelMap map) {
		map.put("person", (User) session.getAttribute("User"));
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 4);
		return "buyer/person-detail";
	}

	@RequestMapping("/buyer/person/detail/do-edit")
	@ResponseBody
	public JsonResult do_edit(PersonEditForm form, HttpServletRequest request) throws DuplicateException,
			InvalidFormException {
		try {
			form.setId(sessionUser(request).getId());
			form.validate();			
			buyer.editPerson(form); 
		} catch (InvalidFormException e) {
			return new JsonResult(false, e.getMessage());
		} catch (DuplicateException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("/buyer/person/register")
	@ResponseBody
	public LoginResult buyer_register(UserRegisterForm form,
			HttpServletResponse response, HttpSession session)
			throws DuplicateException, InvalidFormException {
		User user;
		try {
			form.validate();
			user = buyer.register(form);
			session.setAttribute("User", user);
			setCookie(response, user);
		} catch (InvalidFormException e) {
			return new LoginResult(false, e.getMessage());
		} catch (DuplicateException e) {
			return new LoginResult(false, e.getMessage());
		}
		LoginResult r = new LoginResult(user.getUsername(),
				Base64Util.encode(user.getPassword()));
		return r;
	}

	@RequestMapping("/buyer/person/do-login")
	@ResponseBody
	public LoginResult person_do_login(UserLoginForm form,
			HttpServletResponse response, HttpSession session)
			throws AuthException {
		User user;
		try {
			form.validate();
		} catch (InvalidFormException e1) {
			e1.printStackTrace();
		}
		try {
			user = buyer.login(form);
			session.setAttribute("User", user);
			setCookie(response, user);
		} catch (AuthException e) {
			return new LoginResult(false, e.getMessage());
		}
		LoginResult r = new LoginResult(user.getUsername(),
				Base64Util.encode(user.getPassword()));
		return r;
	}

	@RequestMapping("/buyer/person/do-logout")
	public String person_do_logout(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			throws AuthException {
		session.removeAttribute("User");
		CookieUtil.clear(request, response);
		return "redirect:/index";
	}

	@RequestMapping("/buyer/person/cart")
	public String person_cart(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		if (!checkLogin(request)) {
			return "redirect:/index";
		}
		User user = (User) request.getSession().getAttribute("User");
		map.put("cartList", buyer.getCart(user.getId()));
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 1);
		return "buyer/person-cart";
	}

	@RequestMapping("/buyer/person/do-reset")
	@ResponseBody
	public JsonResult person_do_reset(String password, HttpServletRequest request) {
		User user = sessionUser(request);
		buyer.resetPassword(user.getId(), password);
		return new JsonResult(true);
	}

	@RequestMapping("/buyer/person/cart/outline")
	@ResponseBody
	public CartOutline cart_outline(HttpServletRequest request) {
		if (!checkLogin(request))
			return null;
		User user = sessionUser(request);
		CartOutline outline = buyer.getCartOutline(user.getId());
		return outline;
	}

	@RequestMapping("/buyer/person/cart/remove")
	@ResponseBody
	public JsonResult cart_remove(int cartItemId) {
		buyer.removeCartItem(cartItemId);
		return new JsonResult(true);
	}

	@RequestMapping("/buyer/person/cart/do-add")
	@ResponseBody
	public JsonResult cart_add(int goodsId, int quantity,
			HttpServletRequest request) throws DataAccessException {
		User user = sessionUser(request);
		try {
			buyer.addCartItem(user.getId(), goodsId, quantity);
		} catch (DataAccessException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("/buyer/order/do-order")
	@ResponseBody
	public JsonResult do_order(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		try {
			buyer.makeOrder(((User) session.getAttribute("User")).getId());
		} catch (DataAccessException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("/buyer/order/list")
	public String buyer_order_list(HttpServletRequest request, ModelMap map) {
		List<OrderDetail> o = buyer.getOrderList(sessionUser(request).getId());
		map.put("orderList", o);
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 2);
		return "buyer/order-list";
	}

	@RequestMapping("/buyer/goods/detail")
	public String goods_detail(int goodsId, ModelMap map) {
		map.put("latestList", buyer.getLatestGoods(8));
		GoodsDetail goods = buyer.getGoodsDetail(goodsId);
		map.put("goods", goods);
		commonPage(map, 3);
		map.put("sameCategoryGoods", buyer.getSameCategoryGoods(goods.getCategoryId(), 8));
		return "buyer/goods-detail";
	}

	@RequestMapping("/buyer/goods/search")
	public String goods_search(String keyWord, int page, ModelMap map) {
		GoodsPage r = buyer.searchGoods(keyWord, page);
		map.put("page", r);
		map.put("titleBar", "搜索结果");
		commonPage(map, 3);
		map.put("pageUrl", "/buyer/goods/search?keyWord=" + keyWord + "&page=");
		return "buyer/goods-page";
	}

	@RequestMapping("/buyer/goods/category")
	public String goods_category(int categoryId, int page,
			HttpServletResponse response, ModelMap map) {
		Category c = categoryDao.find(categoryId);
		map.put("page", buyer.getCategoryPage(categoryId, page));
		map.put("titleBar", c.getName());
		commonPage(map, 3);
		map.put("pageUrl", "/buyer/goods/category?categoryId=" + categoryId + "&page=");
		return "buyer/goods-page";
	}

	@RequestMapping({ "/buyer/index", "index", "" })
	public String buyer_index(HttpServletRequest request,
			HttpServletResponse response, ModelMap map) {
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 0);
		return "buyer/index";
	}

	@RequestMapping("/buyer/category")
	public JsonResult buyer_category(HttpServletRequest request,
			HttpServletResponse response, ModelMap map)
			throws DataAccessException {
		try {
			map.put("c1", categoryDao.getAll());
		} catch (DataAccessException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

}
