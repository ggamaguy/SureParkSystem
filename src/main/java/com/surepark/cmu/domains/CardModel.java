package com.surepark.cmu.domains;

public class CardModel {
	
	private String phoneNumber;
	private String cardNumber;
	private String cardExpirationMonth;
	private String cardExpirationYear;
	private String cardValidationCode;
	private String cardHolder;
	
	public CardModel()
	{
		
	}
	
	public CardModel(String phoneNumber, String cardNumber, String cardExpirationMonth, String cardExpirationYear,
			String cardValidationCode, String cardHolder) {
		super();
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.cardExpirationMonth = cardExpirationMonth;
		this.cardExpirationYear = cardExpirationYear;
		this.cardValidationCode = cardValidationCode;
		this.cardHolder = cardHolder;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpirationMonth() {
		return cardExpirationMonth;
	}

	public void setCardExpirationMonth(String cardExpirationMonth) {
		this.cardExpirationMonth = cardExpirationMonth;
	}

	public String getCardExpirationYear() {
		return cardExpirationYear;
	}

	public void setCardExpirationYear(String cardExpirationYear) {
		this.cardExpirationYear = cardExpirationYear;
	}

	public String getCardValidationCode() {
		return cardValidationCode;
	}

	public void setCardValidationCode(String cardValidationCode) {
		this.cardValidationCode = cardValidationCode;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	
	
	
	

}
