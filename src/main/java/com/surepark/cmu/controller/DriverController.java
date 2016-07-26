package com.surepark.cmu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.facades.DriverFacade;
import com.surepark.cmu.facades.ReservationFacade;
import com.surepark.cmu.oauth.OAuth2ServerConfiguration;

@RestController
public class DriverController {
	
	private static final long serialVersionUID = 1L;

	private final String secretKey = "surepark";
	
	@Autowired
	private DriverFacade driverFacade;
	
	@Autowired
	private ReservationFacade reservationFacade;
	
    public DriverController() {
        super();
        
    }

    @RequestMapping(value="/drivers", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String RegisterDriver(@RequestBody JSONObject jsonO ) throws IOException, ParseException{
    	
    	String phoneNumber = "";
    	System.out.println(jsonO.toJSONString());
    	JSONObject jsonroot=new JSONObject();
    	DriverModel driver =null;
    	if(jsonO.containsKey("secretKey") && jsonO.get("secretKey").equals(secretKey))
    	{
    		if(jsonO.containsKey("phoneNumber"))
        	{
        		
        		phoneNumber = jsonO.get("phoneNumber").toString();
        	}
    		DriverModel driverTemp = null;
        	try{
        		driverTemp = driverFacade.findDriver(phoneNumber);
        	}catch(DataAccessException e)
        	{
        		e.printStackTrace();
        	}catch(UsernameNotFoundException e)
        	{
        		e.printStackTrace();
        	}
        	
        	if(driverTemp == null)
        	{
        		String identificatioNumber = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
            	
        		driver = new DriverModel(phoneNumber, identificatioNumber,DriverModel.UNRESERVED);
            	try{
            		driverFacade.registerDiver(driver);
            		jsonroot.put("driverRegistration", "no");
            		jsonroot.put("phoneNumber", driver.getPhoneNumber());
            		jsonroot.put("identificationNumber", driver.getIdentificationNumber());
            		//jsonroot.put("state", driver.getState());
            		System.out.println("새로운 유저가 등록되었습니다.");
            	}catch(DataAccessException e)
            	{
            		e.printStackTrace();
            		jsonroot.put("identificationNumber", "null");
            		//jsonroot.put("state", "null");
            		
            	}
        	}else
        	{
        		driver = driverTemp;
        		jsonroot.put("driverRegistration", "yes");
        		jsonroot.put("phoneNumber", driver.getPhoneNumber());
        		jsonroot.put("identificationNumber", driver.getIdentificationNumber());
        		//jsonroot.put("state", driver.getState());
        	}
        	       
        	
    	}else
    	{
    		jsonroot.put("access", "error");
    	}
    	
    	return jsonroot.toJSONString();
    }
    
    @RequestMapping(value="/drivers/{phoneNumber}", 
    		method = RequestMethod.GET,
    		consumes="application/json")
    public String GetDriver(@PathVariable(value="phoneNumber") String phoneNumber){
    	JSONObject jsonroot=new JSONObject();
    	//System.out.println(userPhoneNumber);
    	try{
    		DriverModel driver = driverFacade.findDriver(phoneNumber);
    		//System.out.println(userDriver.getIdentificationNumber() + userDriver.getPhoneNumber());
    	
    		jsonroot.put("phoneNumber", driver.getPhoneNumber());
    		jsonroot.put("state", driver.getState());
    		if(driver.getState().equals(DriverModel.RESERVED))
    		{
    			List<String> reservationIDList = reservationFacade.getResvId(phoneNumber);
        		jsonroot.put("reservationID", reservationIDList.get(0));
    		}else
    		{
    			jsonroot.put("reservationID", "null");
    		}
    		
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    		jsonroot.put("phoneNumber", "null");
    		jsonroot.put("state","null");
    	}
    	return jsonroot.toJSONString();
    }
    
    @RequestMapping(value="/drivers/{phoneNumber}", 
    		method = RequestMethod.DELETE,
    		consumes="application/json")
    public String DeleteDriver(@PathVariable(value="phoneNumber") String phoneNumber ){
    	
    	JSONObject jsonroot=new JSONObject();
    	try{
    		DriverModel driver = null;
    		try{
    			driver = driverFacade.findDriver(phoneNumber);
    		}catch(DataAccessException e)
        	{
        		e.printStackTrace();
      
        	}catch(UsernameNotFoundException e)
        	{
        		e.printStackTrace();
        	}
    		
    		if(driver != null)
    		{
    			driverFacade.deleteDriver(phoneNumber);
        		jsonroot.put("result", "success");
        		System.out.println(phoneNumber+" 유저가 삭제되었습니다.");
        		
        		//System.out.println(OAuth2ServerConfiguration.AuthorizationServerConfiguration.tokenStore.findTokensByClientIdAndUserName("user_driver", phoneNumber).iterator().next().getValue());
        		OAuth2ServerConfiguration.AuthorizationServerConfiguration.tokenStore.removeAccessToken(OAuth2ServerConfiguration.AuthorizationServerConfiguration.tokenStore.findTokensByClientIdAndUserName("user_driver", phoneNumber).iterator().next());
    		
    		}else
    		{
    			jsonroot.put("result", "notexist");
    			System.out.println(phoneNumber+" 유저가 존재하지않습니다.");
    		}
    		
    		
    		
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    		jsonroot.put("result", "fail");
    		System.out.println(phoneNumber+" 유저가 삭제에 실패했습니다.");
    		
    	}
    	
    	return jsonroot.toJSONString();
    }
    
    @RequestMapping(value="/drivers/handover/{phoneNumber}", 
    		method = RequestMethod.PUT,
    		consumes="application/json")
    public String HandoverUser(@PathVariable(value="phoneNumber") String phoneNumber ,@RequestBody JSONObject jsonO ){
    	JSONObject jsonroot=new JSONObject();
    	if(jsonO.containsKey("secondaryPhoneNumber"))
    	{

        	String secondaryPhoneNumber = jsonO.get("secondaryPhoneNumber").toString();
        	
        	try
        	{
        		driverFacade.handoverDriver(phoneNumber, secondaryPhoneNumber);
        		
        		jsonroot.put("result", "success");
        		
        	}catch(DataAccessException e)
        	{
        		e.printStackTrace();
        		jsonroot.put("result", "fail");
        	}
        	
        	
    		
    	}else
    	{
    		jsonroot.put("result", "fail");
    	}
    	
    	
    	return jsonroot.toJSONString();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
