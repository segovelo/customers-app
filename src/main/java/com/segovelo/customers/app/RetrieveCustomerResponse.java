package com.segovelo.customers.app;


/** 
* 8 Dec 2023 
* @Javadoc TODO 
*
* @author Segovelo  **/

public class RetrieveCustomerResponse {

	private int status;
	
	private String message;
	
	private Customer customer;

	RetrieveCustomerResponse() {
		super();
	}

	RetrieveCustomerResponse(int status, String message, Customer customer) {
		super();
		this.status = status;
		this.message = message;
		this.customer = customer;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
