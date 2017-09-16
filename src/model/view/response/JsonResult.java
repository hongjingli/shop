package model.view.response;

public class JsonResult {
	private boolean success;
	/**
	 * 给前端返回错误信息
	 */
	private String info;		
	public JsonResult(boolean success) {
		this.success = success;
	}
	public JsonResult(boolean success, String info) {
		super();
		this.success = success;
		this.info = info;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
