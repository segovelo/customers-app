package com.segovelo.customers.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
* 8 Dec 2023
* @Javadoc TODO 
*
* @author Segovelo  **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@JsonProperty("customerRef")
	private String customerRef;
		
	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("addressLine1")
	private String addressLine1;
	
	@JsonProperty("addressLine2")
	private String addressLine2;
	
	@JsonProperty("town")
	private String town;
	
	@JsonProperty("county")
	private String county;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("postcode")
	private String postcode;

	public String getCustomerRef() {
		return customerRef;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getTown() {
		return town;
	}

	public String getCounty() {
		return county;
	}

	public String getCountry() {
		return country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setCustomerRef(String customerRef) {
		this.customerRef = customerRef;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "{ customerRef=" + customerRef + ", customerName=" + customerName + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", town=" + town + ", county=" + county
				+ ", country=" + country + ", postcode=" + postcode + "}";
	}

}
