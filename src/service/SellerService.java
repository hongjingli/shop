package service;

import java.util.List;

import model.entity.Goods;
import model.entity.GoodsImage;
import model.view.request.GoodsEditForm;
import model.view.response.CartItemDetail;
import model.view.response.GoodsDetail;
import model.view.response.OrderDetail;

public interface SellerService {

	// 某个商品的详情
	GoodsDetail getGoodsDetail(int goodsId);
	
	// 卖家所有商品（包括未上架）
	List<Goods> getGoodsList(int sellerId);

	void addGoods(Goods goods, List<GoodsImage> moreImages);

	void editGoods(Goods goods, List<GoodsImage> moreImages);

	void removeGoods(int goodsId);

	// 卖家确认已收款
	void confirmPayment(int orderId);

	// 卖家拒绝订单
	void cancelOrder(int orderId);

	// 获取卖家收到的所有订单
	List<OrderDetail> getOrderList(int sellerId);
		
}
