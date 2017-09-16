package service;

import java.util.List;

import exception.AuthException;
import exception.DuplicateException;
import model.entity.Goods;
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

public interface BuyerService {

	CategoryTree getCategoryTree();
	
	// 获取用户的购物车信息
	List<CartItemDetail> getCart(int userId);

	// 获取用户简单的购物车信息
	CartOutline getCartOutline(int userId);

	void removeCartItem(int cartItemId);

	void addCartItem(int buyId, int goodsId, int quantity);

	// 某个商品的详情
	GoodsDetail getGoodsDetail(int goodsId);

	// 在目录：categoryId下的第currrentPage页下的所有商品
	GoodsPage getCategoryPage(int categoryId, int currentPage);

	// 名字含有keyWord的第currentPage页下的所有商品
	GoodsPage searchGoods(String keyWord, int currentPage);

	// 获取最近上架的n个商品，（n <= maxAmount），n尽可能大
	List<Goods> getLatestGoods(int maxAmount);

	// 获取买家发起的所有订单
	List<OrderDetail> getOrderList(int buyerId);

	void makeOrder(int userId);

	User login(UserLoginForm form) throws AuthException;

	void editPerson(PersonEditForm form) throws DuplicateException;

	User register(UserRegisterForm form) throws DuplicateException;

	void resetPassword(int userId, String password);

	List<Goods> getSameCategoryGoods(int categoryId, int maxAmount);

}
