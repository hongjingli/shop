package model.view.request;

public class CategoryEditForm implements IFrom{
	private int id;
	private int parentId;
	private String name;

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

	public void validate() throws InvalidFormException {
		if(name.length()>45||name.length()==0)
			throw new InvalidFormException("不合法的分类名，分类名长度应为1~45");
		else if(id==0)
			throw new InvalidFormException("分类id不能为空");
		else if(parentId==0)
			throw new InvalidFormException("父分类id不能为空");

	}

}
