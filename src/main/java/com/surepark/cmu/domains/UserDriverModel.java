package com.surepark.cmu.domains;


public class UserDriverModel {
	
	private String phoneNumber;
	private String identificationNumber;
	
	public UserDriverModel()
	{
		
	}
	
	public UserDriverModel(String phoneNumber, String identificationNumber)
	{
		
		this.phoneNumber = phoneNumber;
		this.identificationNumber = identificationNumber;
	}
	
	public UserDriverModel(UserDriverModel userDriver)
	{
		super();
		this.phoneNumber = userDriver.getPhoneNumber();
		this.identificationNumber = userDriver.getIdentificationNumber();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	
	
	
	

}
