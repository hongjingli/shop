package dao;

import java.util.List;

import model.entity.Goods;
import model.entity.GoodsImage;

public interface GoodsDao extends BaseDao<Goods>{
	
	List<GoodsImage> getMoreImage(int goodsId);
	
	List<Goods> getLatestGoods(int maxAmount);
	
	List<Goods> findByCategoryId(int categoryId);
	
	List<Goods> search(String keyWord);
	
	List<Goods> findSale(int sellerId);	
	
	void lazyRemove(int goodsId);
	
	void addMoreImage(GoodsImage goodsImage);	
	
	void removeAllMoreImages(int goodsId);
	
	void updateMoreImage(GoodsImage goodsImage);
		
}
