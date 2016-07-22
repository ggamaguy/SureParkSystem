package com.surepark.cmu.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.facades.UserFacade;

@RestController
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserFacade userFacade;
	
    public UserController() {
        super();
        
    }

    @RequestMapping(value="/users", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public boolean RegisterUser(@RequestBody JSONObject jsonO ){
    	
    	String phoneNumber = "";
    	System.out.println(jsonO.toJSONString());
    	if(jsonO.containsKey("user_phoneNumber"))
    	{
    		
    		phoneNumber = jsonO.get("user_phoneNumber").toString();
    	}
    	
    	String identificatioNumber = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
    	
    	UserDriverModel userDriver = new UserDriverModel(phoneNumber, identificatioNumber);
    	try{
    		userFacade.registerUserDiver(userDriver);
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	return true;
    }
    
    @RequestMapping(value="/users/{userPhoneNumber}", 
    		method = RequestMethod.GET,
    		consumes="application/json")
    public boolean GetUser(@RequestBody JSONObject jsonO ){
    	
    	
    	return true;
    }
    
    @RequestMapping(value="/users/{userPhoneNumber}", 
    		method = RequestMethod.DELETE,
    		consumes="application/json")
    public boolean DeleteUser(@RequestBody JSONObject jsonO ){
    	
    	
    	return true;
    }
    
    @RequestMapping(value="/users/handover/{userPhoneNumber}", 
    		method = RequestMethod.PUT,
    		consumes="application/json")
    public boolean HandoverUser(@RequestBody JSONObject jsonO ){
    	
    	
    	return true;
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
