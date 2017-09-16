package model.entity;

//弃用order_no字段
public class GoodsImage {
	private int id;
	private String imageUrl;
	private int goodsId;
	private int orderNo;
	
	
	public GoodsImage(String imageUrl, int goodsId, int orderNo) {
		super();		
		this.imageUrl = imageUrl;
		this.goodsId = goodsId;
		this.orderNo = orderNo;
	}
	
	public GoodsImage(String imageUrl, int goodsId) {
		this();
		this.imageUrl = imageUrl;
		this.goodsId=  goodsId;
	}
	
	public GoodsImage(int id, String imageUrl) {
		this();
		this.imageUrl = imageUrl;
		this.id = id;
		
	}
	
	public GoodsImage(String url) {
		this();		
		this.imageUrl = url;
	}
	public GoodsImage() {
		super();
		this.orderNo = 0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@Override
	public String toString() {
		return "GoodsImage [id=" + id + ", imageUrl=" + imageUrl + ", goodsId="
				+ goodsId + ", orderNo=" + orderNo + "]";
	}
	
	
}
