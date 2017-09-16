package model.view.response;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout.Constraints;

import config.Constant;
import model.entity.Goods;

public class GoodsPage {
	public static final int ROW = 3;
	public static final int COL = 4;
	public static final int SIZE = ROW * COL;
	public static final int PREVIEW_WIDTH = 2;
	
	private List<Goods> goodsList;
	private int currentPage;
	private int minPage;
	private int maxPage;	
	public GoodsPage(List<Goods> goodsList, int currentPage, int minPage,
			int maxPage) {
		super();
		this.goodsList = goodsList;
		this.currentPage = currentPage;
		this.minPage = minPage;
		this.maxPage = maxPage;
	}
	
	public List<Integer> getPreviewList() {
		ArrayList<Integer> r = new ArrayList<Integer>();
		for (int i=minPage;i<=maxPage;i++)
			r.add(i);
		return r;
	}
	
	public int getNextPage() {
		return currentPage == maxPage ? currentPage : currentPage + 1;
	}
	public int getPrevPage() {
		return currentPage == minPage ? currentPage : currentPage - 1;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getMinPage() {
		return minPage;
	}
	public void setMinPage(int minPage) {
		this.minPage = minPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	@Override
	public String toString() {
		return "GoodsPage [goodsList=" + goodsList + ", currentPage="
				+ currentPage + ", minPage=" + minPage + ", maxPage=" + maxPage
				+ "]";
	}
	
}
