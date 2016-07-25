package com.surepark.cmu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.OwnerModel;
import com.surepark.cmu.facades.OwnerFacade;
import com.surepark.cmu.facades.ParkingLotFacade;
import com.surepark.cmu.facades.ParkingLotStatisticFacade;
import com.surepark.cmu.facades.ParkingLotStatusFacade;


/**
 * Servlet implementation class OwnerController
 */
@RestController
public class OwnerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OwnerFacade ownerFacade;
	
	@Autowired
	private ParkingLotFacade parkingLotFacade;
	
	@Autowired
	private ParkingLotStatisticFacade parkingLotStatisticFacade;
	
	@Autowired
	private ParkingLotStatusFacade parkingLotStatusFacade;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OwnerController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
    @RequestMapping(value="/owners/login", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String LoginOwner(@RequestBody JSONObject jsonO){
    	JSONObject jsonroot=new JSONObject();
    	//System.out.println(userPhoneNumber);
    	
    	OwnerModel owner = null;
    	
    	try{
    		
    		if(jsonO.containsKey("ownerID") && jsonO.containsKey("ownerPassword") )
    		{
    			String ownerID = jsonO.get("ownerID").toString();
    			String ownerPassword = jsonO.get("ownerPassword").toString();
    			
    			owner = ownerFacade.loginOwner(ownerID, ownerPassword);
    			
    			if(owner ==null)
    			{
    				jsonroot.put("result", "fail");
    			}else if(owner.getOwnerID().equals(ownerID))
    			{
    				
    				String ownerTwofactorPassword = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    				
    				try
    				{
    					ownerFacade.updateOwnerTwofactor(ownerID, ownerTwofactorPassword);
    					
    					/*
    					try {
							sendMail(owner.getOwnerName(), owner.getOwnerEmail(), "Two Factor Password : "+ownerTwofactorPassword);
						} catch (UnsupportedEncodingException | MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
    					*/
    					
    				}catch(DataAccessException e)
    				{
    					e.printStackTrace();
    				}
    				
    				
    				
    				jsonroot.put("result", "sucess");
    			}
    			
    		}else
    		{
    			jsonroot.put("result", "fail");
    		}
    		
    		
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    		jsonroot.put("result", "fail");
    	}
    	
    	return jsonroot.toJSONString();
    }
    
    @RequestMapping(value="/owners/login/twofactor", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String LoginOwnerTwoFactor(@RequestBody JSONObject jsonO){
    	JSONObject jsonroot=new JSONObject();
    	//System.out.println(userPhoneNumber);
    	
    	OwnerModel owner = null;
    	
    	try{
    		
    		if(jsonO.containsKey("ownerID") && jsonO.containsKey("ownerTwofactorPassword") )
    		{
    			String ownerID = jsonO.get("ownerID").toString();
    			String ownerTwofactorPassword = jsonO.get("ownerTwofactorPassword").toString();
    			
    			System.out.println(jsonO.toJSONString());
    			owner = ownerFacade.loginOwnerTwoFactor(ownerID, ownerTwofactorPassword);
    			
    			System.out.println(owner.toString());
    			
    			if(owner ==null)
    			{
    				jsonroot.put("result", "fail");

    				ownerFacade.updateOwnerTwofactor(ownerID, "");
    			}else if(owner.getOwnerID().equals(ownerID))
    			{  				
    				
    				jsonroot.put("result", "sucess");
    				ownerFacade.updateOwnerTwofactor(ownerID, "");
    				
    			}else
    			{
    				jsonroot.put("result", "fail");

    				ownerFacade.updateOwnerTwofactor(ownerID, "");
    			}
    			
    		}else
    		{
    			jsonroot.put("result", "fail");

    		}
    		
    		
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    		jsonroot.put("result", "fail");
    	}
    	
    	return jsonroot.toJSONString();
    }
    
    
    private void sendMail(String ownerName, String ownerEmail, String Text) throws UnsupportedEncodingException, MessagingException
    {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("chamoonsu@gmail.com");
    	mailSender.setPassword("asdzxc12!");
    
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setFrom("chamoonsu@gmail.com");
    	msg.setTo(new String[] {ownerName,ownerEmail});
    	msg.setSubject("Sure Park Two Factor Password");
    	msg.setText(Text);
    	 
    	try {
    	    mailSender.send(msg);
    	} catch (MailException ex) {
    	    ex.printStackTrace();
    	}
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
