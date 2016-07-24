package com.surepark.cmu.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.facades.DriverFacade;
import com.surepark.cmu.facades.UserFacade;
import com.surepark.cmu.oauth.OAuth2ServerConfiguration;

@RestController
public class DriverController {
	
	private static final long serialVersionUID = 1L;

	private static String secretKey = "surepark";
	
	@Autowired
	private DriverFacade driverFacade;
	
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
            		System.out.println("새로운 유저가 등록되었습니다.");
            	}catch(DataAccessException e)
            	{
            		e.printStackTrace();
            		jsonroot.put("identificationNumber", "null");
            		
            	}
        	}else
        	{
        		driver = driverTemp;
        		jsonroot.put("driverRegistration", "yes");
        		jsonroot.put("phoneNumber", driver.getPhoneNumber());
        		jsonroot.put("identificationNumber", driver.getIdentificationNumber());
        	}
        	
        	/*
            
            try {
            	
            	HttpPost post = new HttpPost("http://localhost:8080/surepark-restful/oauth/token");
            	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            	nvps.add(new BasicNameValuePair("password", userDriver.getIdentificationNumber()));
            	nvps.add(new BasicNameValuePair("username", userDriver.getPhoneNumber()));
            	nvps.add(new BasicNameValuePair("grant_type", "password"));
            	nvps.add(new BasicNameValuePair("scope", "read write"));
            	nvps.add(new BasicNameValuePair("client_secret", "123456"));
            	nvps.add(new BasicNameValuePair("client_id", "user_driver"));

            	String enc = "user_driver:123456";
            	post.setHeader("Authorization", "Basic " + new BASE64Encoder().encode(enc.getBytes()));
            	post.setHeader("Accept", "application/json");
            	post.setHeader("Content-Type", "application/x-www-form-urlencoded");
            	post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            	
            	for(int i =0; i< post.getAllHeaders().length;i++)
            	{
            		System.out.println(post.getAllHeaders()[i].toString());
            	}

            	System.out.println(post.toString() + post.getAllHeaders().toString()+post.getEntity());
            	DefaultHttpClient httpClient = new DefaultHttpClient();
            	
            	
            	
            	HttpResponse response = httpClient.execute(post);
            	
            	System.out.println(response.getStatusLine().getStatusCode());
            	
            	String jsonString = EntityUtils.toString(response.getEntity());

            	System.out.println(jsonString);
               

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			*/
        
        	
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
    	}catch(DataAccessException e)
    	{
    		e.printStackTrace();
    		jsonroot.put("phoneNumber", "null");
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
