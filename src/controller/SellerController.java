package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.Admin;
import model.entity.CartItem;
import model.entity.Goods;
import model.entity.GoodsImage;
import model.entity.User;
import model.view.request.AdminLoginForm;
import model.view.request.CategoryEditForm;
import model.view.request.GoodsEditForm;
import model.view.request.InvalidFormException;
import model.view.request.PersonEditForm;
import model.view.request.UserLoginForm;
import model.view.request.UserRegisterForm;
import model.view.response.CartItemDetail;
import model.view.response.CategoryTree;
import model.view.response.CommonVar;
import model.view.response.GoodsDetail;
import model.view.response.JsonResult;
import model.view.response.OrderDetail;

import org.apache.commons.lang.xwork.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import config.Constant;
import dao.GoodsDao;
import exception.AuthException;
import exception.DuplicateException;
import util.E;
import util.ImageUtil;
import service.*;
import service.impl.SellerServiceImpl;

@Controller
public class SellerController {
	@Autowired
	private SellerService seller;
	@Autowired
	private BuyerService buyer;

	private void commonPage(ModelMap map, int navActive) {
		map.put("com", new CommonVar(buyer.getCategoryTree(), navActive));
	}

	@RequestMapping("/seller/goods/list")
	public String goods_list(HttpSession session, ModelMap map) {
		List<Goods> g = seller.getGoodsList(((User) session
				.getAttribute("User")).getId());
		map.put("goodsList", g);
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 5);
		return "seller/goods-list";
	}

	@RequestMapping("/seller/goods/add")
	public String goods_add(ModelMap map) {
		CategoryTree ct = buyer.getCategoryTree();
		map.put("ct", ct);
		return "seller/goods-add";
	}

	private JsonResult imageError() {
		return new JsonResult(false, "请上传正确的商品图片,只支持jpg, png, gif格式");
	}
	
	@RequestMapping("/seller/goods/mainImage")
	@ResponseBody
	public JsonResult mainImage(@RequestParam("mainImage") MultipartFile file, HttpSession session) {

		if (file == null || file.getSize() == 0) {
			return new JsonResult(true);	//这里交给do-add和do-edit处理
		}
		String url = saveImage(file);		
		session.setAttribute("mainImage", url);
		return new JsonResult(true);
	}

	@RequestMapping("/seller/goods/moreImage")
	@ResponseBody
	public JsonResult goods_more_image(@RequestParam("moreImage[]") MultipartFile[] files, HttpServletRequest request) {		
		String ids [] = request.getParameterValues("moreImage-id[]");
		//没有上传或上传失败，moreImage[i] = null
		List<GoodsImage> moreImage = new ArrayList<GoodsImage>();
		
		if (files.length != 3)		//数量固定为3
			return new JsonResult(false);
		
		
		for (int i=0;i<files.length;i++) {
			MultipartFile file= files[i];
			if (file != null && file.getSize() > 0) {
				String url = saveImage(file);
				if (url == null)
					moreImage.add(null);
				else {
					
					if (ids[0] ==  "-1") {		//add模式	
						
						moreImage.add(new GoodsImage(url));
					} else {					//edit模式
						moreImage.add(new GoodsImage(Integer.parseInt(ids[i]), url));
					}
				}
									
			} else {
				moreImage.add(null);
			}
		}
		request.getSession().setAttribute("moreImage", moreImage);
		return new JsonResult(true);
	}

	@RequestMapping("/seller/goods/do-edit")
	@ResponseBody
	public JsonResult goods_do_edit(GoodsEditForm form, HttpSession session) throws IllegalStateException, IOException {
		String mainImage = (String) session.getAttribute("mainImage");
		List<GoodsImage> moreImages=  (List<GoodsImage>) session.getAttribute("moreImage");
		try {
			form.validate();
		} catch (InvalidFormException e) {
			return new JsonResult(false, e.getMessage());
		}		
		Goods goods = form.toGoods(mainImage, sessionUser(session).getId());		
		seller.editGoods(goods, moreImages);
		session.setAttribute("mainImage", null);
		session.setAttribute("moreImage", null);
		return new JsonResult(true);
	}
	private void buildDirs(String path) {
		File file = new File(path);
		if (!file.isDirectory() || !file.exists()) {
			file.mkdirs();
		}
	}
	
	private String saveImage(MultipartFile item) {
		String year_month = new SimpleDateFormat("yyyy-MM").format(new Date());
		String day = new SimpleDateFormat("dd").format(new Date());
		String filename = item.getOriginalFilename();
		String suffix[] = { ".jpg", ".gif", ".png" };
		String s = null;
		for (String suf : suffix) {
			if (filename.endsWith(suf)) {
				s = suf;
				break;
			}
		}

		if (s != null) {
			String datePath = "/" + year_month + "/" + day;
			String leftPath = Constant.Path.serverGoodsImg() + datePath;	
			buildDirs(leftPath);
			filename = RandomStringUtils.randomAlphanumeric(10) + s;
			File file = new File(leftPath, filename);
			String url = Constant.Path.clientGoodsImg() + datePath + "/" + filename;
			try {
				item.transferTo(file);
				/**
				 * 大小处理
				 */
//				ImageUtil.ct116Image(file.getAbsolutePath());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			return url;
		}
		return null;
	}
	
	
	private User sessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("User");
	}
	
	private User sessionUser(HttpSession session) {
		return (User) session.getAttribute("User");
	}
	
	@RequestMapping("/seller/goods/do-add")
	@ResponseBody
	public JsonResult goods_do_add(GoodsEditForm form, HttpSession session) throws IllegalStateException,
			IOException {
		String mainImage = (String) session.getAttribute("mainImage");
		if (mainImage == null) {
			return imageError();
		}
		List<GoodsImage> moreImages=  (List<GoodsImage>) session.getAttribute("moreImage");
		try {
			form.validate();
		} catch (InvalidFormException e) {
			return new JsonResult(false, e.getMessage());
		}
		Goods goods = form.toGoods(mainImage, sessionUser(session).getId());		
		seller.addGoods(goods, moreImages);
		session.setAttribute("mainImage", null);
		session.setAttribute("moreImage", null);
		return new JsonResult(true);
	}

	@RequestMapping("/seller/goods/edit")
	public String goods_edit(int goodsId, ModelMap map) {
		GoodsDetail gd = seller.getGoodsDetail(goodsId);
		map.put("goods", gd);
		map.put("ct", buyer.getCategoryTree());
		return "seller/goods-edit";
	}

	@ResponseBody
	@RequestMapping("/seller/goods/do-remove")
	public JsonResult goods_remove(int goodsId) {
		seller.removeGoods(goodsId);
		return new JsonResult(true);
	}


	@RequestMapping("/seller/order/list")
	public String seller_order_list(HttpSession session, ModelMap map) {
		map.put("orderList", seller.getOrderList(((User) session
				.getAttribute("User")).getId()));
		map.put("latestList", buyer.getLatestGoods(8));
		commonPage(map, 6);
		return "seller/order-list";
	}

	@RequestMapping("/seller/order/confirm")
	@ResponseBody
	public JsonResult order_confirm(int orderId, ModelMap map) {
		try {
			seller.confirmPayment(orderId);
		} catch (DataAccessException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

	@RequestMapping("/seller/order/cancel")
	@ResponseBody
	public JsonResult order_cancel(int orderId, ModelMap map) {
		try {
			seller.cancelOrder(orderId);
		} catch (DataAccessException e) {
			return new JsonResult(false, e.getMessage());
		}
		return new JsonResult(true);
	}

}
