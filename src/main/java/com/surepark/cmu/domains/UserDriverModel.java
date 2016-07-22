package com.surepark.cmu.domains;


public class UserDriverModel {
	
	private String phoneNumber;
	private String identificatioNumber;
	
	public UserDriverModel(String phoneNumber, String identificatioNumber)
	{
		this.phoneNumber = phoneNumber;
		this.identificatioNumber = identificatioNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdentificatioNumber() {
		return identificatioNumber;
	}

	public void setIdentificatioNumber(String identificatioNumber) {
		this.identificatioNumber = identificatioNumber;
	}
	
	
	
	

}
