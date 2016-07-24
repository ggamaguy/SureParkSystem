package com.surepark.cmu.domains;

public class DriverModel {
	
	final public static String UNRESERVED = "unreserved";
	final public static String RESERVED = "reserved";
	final public static String PARKED = "parked";
	final public static String PAYING = "paying";
	
	private String phoneNumber;
	private String identificationNumber;
	private String state;
	
	public DriverModel()
	{
		
	}
	
	
	public DriverModel(String phoneNumber, String identificationNumber, String state) {
		super();
		this.phoneNumber = phoneNumber;
		this.identificationNumber = identificationNumber;
		this.state = state;
	}
	
	public DriverModel(DriverModel driverModel)
	{
		this.phoneNumber = driverModel.phoneNumber;
		this.identificationNumber = driverModel.identificationNumber;
		this.state = driverModel.state;
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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
	

}
