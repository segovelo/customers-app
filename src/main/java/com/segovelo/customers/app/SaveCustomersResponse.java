package com.segovelo.customers.app;


/** 
* 8 Dec 2023 02:35:07
* @Javadoc TODO 
*
* @author Segovelo  **/

public class SaveCustomersResponse {
	private String message;
	private int status;
	
	public  SaveCustomersResponse() {
		super();	
	}
	public SaveCustomersResponse(int status, String message) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public int getStatus() {
		return status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
