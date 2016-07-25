package com.surepark.cmu.payment;

import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.CardModel;

@Service
public class PaymentAPI {
	
	
	
	public boolean payment(CardModel card, int money)
	{
		boolean result = false;
		
		if(card.getCardExpirationMonth() !=null && card.getCardExpirationYear() !=null && card.getCardHolder() !=null && card.getCardNumber() !=null & card.getCardValidationCode() !=null && card.getPhoneNumber() != null)
		{
			//TODO payment API
			
			System.out.println("정상 카드 입니다.");
			result = true;
		}
		
		
		return result;
	}

}
