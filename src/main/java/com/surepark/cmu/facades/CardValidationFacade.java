package com.surepark.cmu.facades;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.surepark.cmu.domains.CardValidationModel;
import com.surepark.cmu.interfaces.CardValidationInterface;


@Service
public class CardValidationFacade implements CardValidationInterface{

	@Autowired
	@Qualifier("firstSqlSessionTemplate")
	SqlSession sqlSession;
	

	@Override
	public void insertValidationCard(CardValidationModel cardValidationModel) throws DataAccessException {
		// TODO Auto-generated method stub
		
		sqlSession.insert("CardValidationFacade.insertValidationCard", cardValidationModel);
		
	}

	@Override
	public CardValidationModel findValidationCard(String cardNumber, String phoneNumber) throws DataAccessException {
		// TODO Auto-generated method stub
		
		CardValidationModel tempCard = new CardValidationModel();
		tempCard.setCardNumber(cardNumber);
		tempCard.setPhoneNumber(phoneNumber);
		
		CardValidationModel validationCard = null;
		validationCard = sqlSession.selectOne("CardValidationFacade.findValidationCard", tempCard);
		if (validationCard == null) {
			throw new UsernameNotFoundException(String.format("Card Validation %s in %s does not exist!", cardNumber,phoneNumber));
		}
		//System.out.println(user.toString());
		
		return validationCard;
	}

	@Override
	public void deleteCardByPhoneNumber(String phoneNumber) throws DataAccessException {
		sqlSession.delete("CardValidationFacade.deleteCardByPhoneNumber",phoneNumber);
	}


	
	
}
