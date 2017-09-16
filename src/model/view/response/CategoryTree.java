package model.view.response;

import java.util.HashSet;
import java.util.Set;

import model.entity.Category;

//为了兼容前端，分类目录最多只能有2层
public class CategoryTree {
	private Category curr;
	private Set<CategoryTree> child;
	
	public CategoryTree() {
		child = new HashSet<CategoryTree>();
	}
	
	public CategoryTree(Category c) {
		child = new HashSet<CategoryTree>();
		this.curr = c;
	}
	public Category getCurr() {
		return curr;
	}
	public void setCurr(Category curr) {
		this.curr = curr;
	}
	public Set<CategoryTree> getChild() {
		return child;
	}
	public void setChild(Set<CategoryTree> child) {
		this.child = child;
	}
}
