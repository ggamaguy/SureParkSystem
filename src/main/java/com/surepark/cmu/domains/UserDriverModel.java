package com.surepark.cmu.domains;


public class UserDriverModel {
	
	private String phoneNumber;
	private String identificationNumber;
	private String role;
	
	public UserDriverModel()
	{
		
	}
	
	public UserDriverModel(String phoneNumber, String identificationNumber, String role)
	{
		
		this.phoneNumber = phoneNumber;
		this.identificationNumber = identificationNumber;
		this.role = role;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getPhoneNumber() + " : "+getIdentificationNumber()+" " + role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
	
	

}
