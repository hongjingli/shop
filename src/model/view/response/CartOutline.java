package model.view.response;

public class CartOutline {
	private double sumCost;
	private int itemNum;
	
	public CartOutline() {
		super();
		
	}
	public CartOutline(double sumCost, int itemNum) {
		super();
		this.sumCost = sumCost;
		this.itemNum = itemNum;
	}
	public double getSumCost() {
		return sumCost;
	}
	public void setSumCost(double sumCost) {
		this.sumCost = sumCost;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	@Override
	public String toString() {
		return "CartOutline [sumCost=" + sumCost + ", itemNum=" + itemNum + "]";
	}
	
}
