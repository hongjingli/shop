package model.view.request;

public class InvalidFormException extends Exception{
    private String message;
	public InvalidFormException(String msg) {
		super(msg);
		this.message=msg;
	}
	public String getMessage() {
		return this.message;
	}	
}
