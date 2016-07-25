package com.surepark.cmu.domains;



public class CardValidationModel {
	
	final public static String VALID = "valid";
	final public static String INVALID = "invalid";
	
	private String cardNumber;
	private String validationState;
	private String validationTime;
	private String phoneNumber;
	
	public CardValidationModel()
	{
		
	}
	
	public CardValidationModel(String cardNumber, String validationState, String validationTime,String phoneNumber) {
		super();
		this.cardNumber = cardNumber;
		this.validationState = validationState;
		this.validationTime = validationTime;
		this.phoneNumber = phoneNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getValidationState() {
		return validationState;
	}

	public void setValidationState(String validationState) {
		this.validationState = validationState;
	}

	public String getValidationTime() {
		return validationTime;
	}

	public void setValidationTime(String validationTime) {
		this.validationTime = validationTime;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CardValidationModel [cardNumber=" + cardNumber + ", validationState=" + validationState
				+ ", validationTime=" + validationTime + ", phoneNumber=" + phoneNumber + "]";
	}

	
	
	

	
	
	


}
