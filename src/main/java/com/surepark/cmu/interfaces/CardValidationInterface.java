package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.CardValidationModel;

public interface CardValidationInterface {

	
	public void insertValidationCard(CardValidationModel cardValidationModel) throws DataAccessException;
	public CardValidationModel findValidationCard(String cardNumber,String phoneNumber) throws DataAccessException;
	public void deleteCardByPhoneNumber(String phoneNumber)throws DataAccessException;
}
