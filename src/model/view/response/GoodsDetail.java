package model.view.response;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import util.E;
import dao.CategoryDao;
import dao.GoodsDao;
import dao.UserDao;
import model.entity.Category;
import model.entity.Goods;
import model.entity.GoodsImage;
import model.entity.User;

public class GoodsDetail {
	private int id;
	private String  name;
	private String  description;
	private int status;
	private int quantity;
	private String mainImage;
	private double price;
	private List<GoodsImage> moreImage;	
	private List<Category> cl;
	private Category category;
	private User seller;
	
	public static GoodsDetail fromGoodsId(int goodsId, GoodsDao goodsDao, CategoryDao categoryDao, UserDao userDao) {
		Goods goods = goodsDao.find(goodsId);

		LinkedList<Category> cRealList = new LinkedList<Category>();
		Map<Integer, Category> m = categoryDao.getMap();
		Category curr = m.get(goods.getCategoryId());
		while(curr.getId() != curr.getParentId()) {
			cRealList.addFirst(curr);
			curr = m.get(curr.getParentId());
		}
		cRealList.addFirst(curr);
		List<GoodsImage> moreImage = goodsDao.getMoreImage(goods.getId());
		User seller = userDao.find(goods.getSellerId());		
		return new GoodsDetail(goods, moreImage, seller, cRealList, cRealList.getLast());
	}
	
	private GoodsDetail(Goods goods, List<GoodsImage> moreImage, User seller, List<Category> cl, Category category) {
		this.id = goods.getId();
		this.name = goods.getName();
		this.description = goods.getDescription();
		this.status = goods.getStatus();
		this.quantity = goods.getQuantity();
		this.mainImage = goods.getMainImage();
		this.price = goods.getPrice();
		this.moreImage = moreImage;
		this.seller = seller;		
		this.cl = cl;
		this.category = category;
	}
	
	public GoodsDetail() {
		super();
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Category> getCl() {
		return cl;
	}
	public void setCl(List<Category> cl) {
		this.cl = cl;
	}
	public User getSeller() {
		return seller;
	}
	public void setSeller(User seller) {
		this.seller = seller;
	}

	public List<GoodsImage> getMoreImage() {
		return moreImage;
	}

	public void setMoreImage(List<GoodsImage> moreImage) {
		this.moreImage = moreImage;
	}

	@Override
	public String toString() {
		return "GoodsDetail [id=" + id + ", name=" + name + ", description="
				+ description + ", status=" + status + ", quanity=" + quantity
				+ ", mainImage=" + mainImage + ", price=" + price
				+ ", moreImage=" + moreImage + ", cl=" + cl + ", seller="
				+ seller + "]";
	}

	public int getCategoryId() {
		return category.getId();
	}
	
}
