package service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CartItemDao;
import dao.CategoryDao;
import dao.GoodsDao;
import dao.OrderDao;
import dao.UserDao;
import model.entity.CartItem;
import model.entity.Goods;
import model.entity.GoodsImage;
import model.entity.Order;
import model.view.request.GoodsEditForm;
import model.view.response.CartItemDetail;
import model.view.response.GoodsDetail;
import model.view.response.OrderDetail;
import service.SellerService;
import util.E;

@Service
public class SellerServiceImpl implements SellerService {

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
	
	
	public GoodsDetail getGoodsDetail(int goodsId) {
		return GoodsDetail.fromGoodsId(goodsId, goodsDao, categoryDao, userDao);
	}

	
	public List<Goods> getGoodsList(int sellerId) {
		
		List<Goods> goodsList = goodsDao.findSale(sellerId);
		
		return goodsList;
		
	}

	public void removeGoods(int goodsId) {
//		goodsDao.removeAllMoreImages(goodsId);
//		
//		goodsDao.remove(goodsId);
		
		goodsDao.lazyRemove(goodsId);
		
	}

	public void confirmPayment(int orderId) {
		Order o = orderDao.find(orderId);
		
		o.setStatus(Order.SUCCESS);
		
		orderDao.update(o);
	}

	public void cancelOrder(int orderId) {
		Order o = orderDao.find(orderId);
		
		o.setStatus(Order.SELLER_REJECT);
		
		orderDao.update(o);
		
	}

	public List<OrderDetail> getOrderList(int sellerId) {
		
		List<Order> orders = orderDao.findOrderBySeller(sellerId);
		
		return OrderDetail.fromOrderList(orders, orderDao, userDao, goodsDao);
	}

	public void addGoods(Goods goods, List<GoodsImage> moreImage) {
		goodsDao.add(goods);
		for (GoodsImage g : moreImage) {
			//即使空也要加上去，保证有3个
			if (g == null) {
				g = new GoodsImage("", goods.getId());
			} 
			g.setGoodsId(goods.getId());
			goodsDao.addMoreImage(g);
		}
	}

	public void editGoods(Goods goods, List<GoodsImage> moreImage) {	
		
		new E(goods);
		new E(moreImage);
		
		Goods origin = goodsDao.find(goods.getId());
		
		if (goods.getMainImage() == null) {
			goods.setMainImage(origin.getMainImage());
		}
		
		goodsDao.update(goods);
		
		for (GoodsImage i : moreImage) {			
			if (i != null) {
				i.setGoodsId(goods.getId());
				goodsDao.updateMoreImage(i);				
			}
				
		}
		
	}
}
