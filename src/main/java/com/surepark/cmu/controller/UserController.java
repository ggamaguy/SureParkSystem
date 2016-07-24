package com.surepark.cmu.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.facades.UserFacade;

import sun.misc.BASE64Encoder;

@RestController
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String secret_key = "surepark";
	
	@Autowired
	private UserFacade userFacade;
	
    public UserController() {
        super();
        
    }

    @RequestMapping(value="/users", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String RegisterUser(@RequestBody JSONObject jsonO ) throws IOException, ParseException{
    	
    	String phoneNumber = "";
    	System.out.println(jsonO.toJSONString());
    	JSONObject jsonroot=new JSONObject();
    	UserDriverModel userDriver =null;
    	if(jsonO.containsKey("secret_key") && jsonO.get("secret_key").equals(secret_key))
    	{
    		if(jsonO.containsKey("user_phoneNumber"))
        	{
        		
        		phoneNumber = jsonO.get("user_phoneNumber").toString();
        	}
        	UserDriverModel userTemp = null;
        	try{
        		userTemp = userFacade.findUserDriver(phoneNumber);
        	}catch(DataAccessException e)
        	{
        		e.printStackTrace();
        	}catch(UsernameNotFoundException e)
        	{
        		e.printStackTrace();
        	}
        	
        	if(userTemp == null)
        	{
        		String identificatioNumber = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
            	
            	userDriver = new UserDriverModel(phoneNumber, identificatioNumber,"ROLE_USER");
            	try{
            		userFacade.registerUserDiver(userDriver);
            		jsonroot.put("user_registration", "no");
            		jsonroot.put("user_phoneNumber", userDriver.getPhoneNumber());
            		jsonroot.put("user_identification Number", userDriver.getIdentificationNumber());
            		System.out.println("새로운 유저가 등록되었습니다.");
            	}catch(DataAccessException e)
            	{
            		e.printStackTrace();
            		jsonroot.put("user_identification Number", "null");
            		
            	}
        	}else
        	{
        		userDriver = userTemp;
        		jsonroot.put("user_registration", "yes");
        		jsonroot.put("user_phoneNumber", userTemp.getPhoneNumber());
        		jsonroot.put("user_identification Number", userTemp.getIdentificationNumber());
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
    
    @RequestMapping(value="/users/{userPhoneNumber}", 
    		method = RequestMethod.GET,
    		consumes="application/json")
    public String GetUser(@PathVariable(value="userPhoneNumber") String userPhoneNumber){
    	
    	//System.out.println(userPhoneNumber);
    	UserDriverModel userDriver = userFacade.findUserDriver(userPhoneNumber);
    	//System.out.println(userDriver.getIdentificationNumber() + userDriver.getPhoneNumber());
    	JSONObject jsonroot=new JSONObject();
    	jsonroot.put("user_phoneNumber", userDriver.getPhoneNumber());
    	return jsonroot.toJSONString();
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
