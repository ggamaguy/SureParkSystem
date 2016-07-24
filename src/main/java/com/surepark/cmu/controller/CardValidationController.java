package com.surepark.cmu.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.CardModel;
import com.surepark.cmu.domains.CardValidationModel;
import com.surepark.cmu.facades.CardValidationFacade;
import com.surepark.cmu.payment.PaymentAPI;

/**
 * Servlet implementation class CardValidationController
 */
@RestController
public class CardValidationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CardValidationFacade cardValidationFacade;
	
	@Autowired
	private PaymentAPI paymentAPI;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CardValidationController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @RequestMapping(value="/cardvalidate", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String validateCard(@RequestBody JSONObject jsonO)
    {
    	JSONObject jsonroot=new JSONObject();
    	
    	if(jsonO.containsKey("phoneNumber") && jsonO.containsKey("cardNumber") && jsonO.containsKey("cardExpirationMonth") && jsonO.containsKey("cardExpirationYear") && jsonO.containsKey("cardValidationCode") && jsonO.containsKey("cardHolder"))
    	{
    		
    		System.out.println(jsonO.toJSONString());
    		String phoneNumber = jsonO.get("phoneNumber").toString();
    		String cardNumber = jsonO.get("cardNumber").toString();
    		String cardExpirationMonth = jsonO.get("cardExpirationMonth").toString();
    		String cardExpirationYear = jsonO.get("cardExpirationYear").toString();
    		String cardValidationCode = jsonO.get("cardValidationCode").toString();
    		String cardHolder = jsonO.get("cardHolder").toString();
    		
    		CardModel card = new CardModel(phoneNumber, cardNumber, cardExpirationMonth, cardExpirationYear, cardValidationCode, cardHolder);
    		
    		
    		boolean result = paymentAPI.payment(card , 1);
    		
    		CardValidationModel cardValidationModel = new CardValidationModel();
    		cardValidationModel.setCardNumber(cardNumber);
    		cardValidationModel.setPhoneNumber(phoneNumber);
    		java.util.Date dt = new java.util.Date();

			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String validationTime = sdf.format(dt);
			
			cardValidationModel.setValidationTime(validationTime);
			
    		
    		if(result)
    		{
    			jsonroot.put("result", "success");
    			
    			cardValidationModel.setValidationState(CardValidationModel.VALID);
    			
    			System.out.println(cardValidationModel.toString());
    			
    			cardValidationFacade.insertValidationCard(cardValidationModel);
    			
    		}else
    		{
    			
    			jsonroot.put("result", "fail");
    			
    			cardValidationModel.setValidationState(CardValidationModel.INVALID);
    			System.out.println(cardValidationModel.toString());
    			cardValidationFacade.insertValidationCard(cardValidationModel);
    		}
    		
    	}else
    	{
    		jsonroot.put("result", "fail");
    	}
    	
    	return jsonroot.toJSONString();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
