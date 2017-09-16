package model.entity;

public class Category {
	private int id;
	private int parentId;
	private String name;
	
	public Category( int parentId, String name) {
		super();
		this.parentId = parentId;
		this.name = name;
	}
	
	public Category() {
		super();
		
	}

	public boolean isTop() {
		return getParentId() == getId();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", name="
				+ name + "]";
	}
}
