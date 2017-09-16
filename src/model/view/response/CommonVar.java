package model.view.response;

public class CommonVar {
	private CategoryTree ct;
	private int navActive;
	
	public CommonVar(CategoryTree ct, int navActive) {
		super();
		this.ct = ct;
		this.navActive = navActive;
	}
	public CategoryTree getCt() {
		return ct;
	}
	public void setCt(CategoryTree ct) {
		this.ct = ct;
	}
	public int getNavActive() {
		return navActive;
	}
	public void setNavActive(int navActive) {
		this.navActive = navActive;
	}
	
}
