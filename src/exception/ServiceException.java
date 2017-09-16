package exception;

import model.view.response.JsonResult;

public class ServiceException extends Exception{
	public ServiceException(String msg) {
		super(msg);
	}
	
	public JsonResult toJsonResult() {
		return new JsonResult(false, this.getMessage());
	}
}
