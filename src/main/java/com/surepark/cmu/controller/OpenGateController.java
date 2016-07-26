package com.surepark.cmu.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.facades.DriverFacade;
import com.surepark.cmu.facades.ReservationFacade;

import scala.annotation.meta.setter;

/**
 * Servlet implementation class OpenGateController
 */
public class OpenGateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Autowired
	private ReservationFacade reservationFacade;
	
	@Autowired
	private DriverFacade driverFacade; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenGateController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @RequestMapping(value="/opengate/{phoneNumber}", 
    		method = RequestMethod.GET,
    		consumes="application/json")
    public String openGate(@PathVariable(value="phoneNumber") String phoneNumber)
    {
    	
    	JSONObject result = null;
    	
    	DriverModel driverModel = driverFacade.findDriver(phoneNumber);
    	
    	if(driverModel.getState().equals(DriverModel.RESERVED))
    	{
    		result = reservedOpenTheGate(phoneNumber);
    		
    	}else if (driverModel.getState().equals(DriverModel.PARKED))
    	{
    		result = parkedOpenTheGate(phoneNumber);
    	}else if(driverModel.getState().equals(DriverModel.UNRESERVED))
    	{
    		result = unreservedOpenTheGate();
    	}else if(driverModel.getState().equals(DriverModel.PAYING))
    	{
    		result = payingOpenTheGate();
    	}
    
    	
    	
    	
		return result.toJSONString();
    	
    }
    
    public JSONObject reservedOpenTheGate(String phoneNumber)
    {
    	JSONObject result = new JSONObject();
    	
    	JSONObject jsonO = new JSONObject();
    	
    	jsonO.put("type", "4");
    	jsonO.put("phoneNumber", phoneNumber);
    	jsonO.put("state", DriverModel.RESERVED);
    	
    	
    	Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 5050);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(jsonO.toJSONString());
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String response = null;
		try {
			response = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);
		
		
    	JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject recvJsonObject = (JSONObject) obj;
		
		if(recvJsonObject.containsKey("result") && recvJsonObject.get("result").equals("success"))
		{
			String state = recvJsonObject.get("state").toString();
			String entranceTime = recvJsonObject.get("entranceTime").toString();
			ReservationModel rm = new ReservationModel();
			rm.setPhoneNumber(phoneNumber);
			rm.setEntranceTime(Timestamp.valueOf(entranceTime));
			reservationFacade.updateEntranceTime(rm);
			driverFacade.updateDriverState(phoneNumber, DriverModel.PARKED);
			
			result.put("result", "success");
			
		}else if (recvJsonObject.containsKey("result") && recvJsonObject.get("result").equals("fail"))
		{
			result.put("result", "fail");
		}
    	
    	
    	return result;
    }
    
    public JSONObject parkedOpenTheGate(String phoneNumber)
    {
    	JSONObject result = new JSONObject();
    	
    	JSONObject jsonO = new JSONObject();
    	
    	jsonO.put("type", "5");
    	jsonO.put("phoneNumber", phoneNumber);
    	jsonO.put("state", DriverModel.PARKED);
    	
    	jsonO.put("phoneNumber", phoneNumber);
    	
    	Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 5050);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(jsonO.toJSONString());
		
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String response = null;
		try {
			response = input.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(response);		
    	
    	
    	return result;
    }
    
    
    public JSONObject unreservedOpenTheGate()
    {
    	JSONObject result = new JSONObject();
    	
    	
    	return result;
    }
    
    public JSONObject payingOpenTheGate()
    {
    	JSONObject result = new JSONObject();
    	
    	
    	return result;
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
