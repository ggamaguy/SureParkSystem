package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.CardValidationModel;

public interface CardValidationInterface {

	
	void insertValidationCard(CardValidationModel cardValidationModel) throws DataAccessException;
	CardValidationModel findValidationCard(String cardNumber,String phoneNumber) throws DataAccessException;
}
