package com.surepark.cmu.domains;

public class HandoverDriverModel {
	
	private String phoneNumber;
	private String secondaryPhoneNumber;
	
	
	public HandoverDriverModel()
	{
		
	}
	
	
	public HandoverDriverModel(String phoneNumber, String secondaryPhoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}


	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	
	
	
	
	

}
