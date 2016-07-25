package com.surepark.cmu.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.facades.DriverFacade;
import com.surepark.cmu.interfaces.ReservationInterface;

@RestController
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ReservationModel reservationModel;
    
    @Autowired
    private ReservationInterface reservationFacade;
    
    @Autowired
    private DriverFacade driverFacade;
    
    public ReservationController() {
        super();

    }
    
    @RequestMapping(value="/reservations", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String makeReservation(@RequestBody JSONObject jsonO ){
    	JSONObject result = new JSONObject();
    	String reservationId = new String();
    	reservationModel = new ReservationModel();
    	if(jsonO.containsKey("phoneNumber") && jsonO.get("phoneNumber") != null){
    		reservationModel.setPhoneNumber(jsonO.get("phoneNumber").toString());
    		System.out.println(jsonO.get("phoneNumber").toString());
    	}
    	if(jsonO.containsKey("email") && jsonO.get("email") != null){
    		reservationModel.setEmail(jsonO.get("email").toString());
    		System.out.println(jsonO.get("email").toString());
    	}
    	if(jsonO.containsKey("parkingLotID") && jsonO.get("parkingLotID") != null){
    		reservationModel.setParkingLotID(jsonO.get("parkingLotID").toString());
    		System.out.println(jsonO.get("parkingLotID").toString());
    	}
    	if(jsonO.containsKey("carSize") && jsonO.get("carSize") != null){
    		reservationModel.setCarSize(Integer.parseInt(jsonO.get("carSize").toString()));
    		System.out.println(jsonO.get("carSize").toString());
    	}
    	if(jsonO.containsKey("reservationTime") && jsonO.get("reservationTime") != null){
    		reservationModel.setReservationTime(Timestamp.valueOf(jsonO.get("reservationTime").toString()));
    		System.out.println(jsonO.get("reservationTime").toString());
    	}
    	/*
    	if(jsonO.containsKey("entranceTime") && jsonO.get("exitTime") != null){
    		reservationModel.setEntranceTime(Timestamp.valueOf(jsonO.get("entranceTime").toString()));
    		System.out.println(Timestamp.valueOf(jsonO.get("entranceTime").toString()));
    	}
    	if(jsonO.containsKey("exitTime") && jsonO.get("exitTime") != null){
    		reservationModel.setExitTime(Timestamp.valueOf(jsonO.get("exitTime").toString()));
    		System.out.println(jsonO.get("exitTime").toString());
    	}
    	*/
    	if(jsonO.containsKey("cardNumber") && jsonO.get("cardNumber") != null){
    		reservationModel.setCardNumber(jsonO.get("cardNumber").toString());
    		System.out.println(jsonO.get("cardNumber").toString());
    	}
    	if(jsonO.containsKey("cardExpirationMonth") && jsonO.get("cardExpirationMonth") != null){
    		reservationModel.setCardExpirationMonth(jsonO.get("cardExpirationMonth").toString());
    		System.out.println(jsonO.get("cardExpirationMonth").toString());
    	}
    	if(jsonO.containsKey("cardExpirationYear") && jsonO.get("cardExpirationYear") != null){
    		reservationModel.setCardExpirationYear(jsonO.get("cardExpirationYear").toString());
    		System.out.println(jsonO.get("cardExpirationYear").toString());
    	}
    	if(jsonO.containsKey("cardValidationCode") && jsonO.get("cardValidationCode") != null){
    		reservationModel.setCardValidationCode(jsonO.get("cardValidationCode").toString());
    		System.out.println(jsonO.get("cardValidationCode").toString());
    	}
    	if(jsonO.containsKey("cardHolder") && jsonO.get("cardHolder") != null){
    		reservationModel.setCardHolder(jsonO.get("cardHolder").toString());
    		System.out.println(jsonO.get("cardHolder").toString());
    	}
    	try{
    		reservationFacade.insertResv(reservationModel);
    		reservationId = reservationFacade.getResvId(reservationModel.getPhoneNumber()).get(0);
    		
    		try{
    			driverFacade.updateDriverState(reservationModel.getPhoneNumber(), DriverModel.RESERVED);
    		}catch(DataAccessException e)
    		{
    			e.printStackTrace();
    		}
    	}catch(Exception e){
    		result.put("result", "fail");
    		result.put("reservationID", null);
    		e.printStackTrace();
    		return result.toJSONString();
    	}
    	result.put("result", "success");
    	result.put("reservationID", reservationId);
    	return result.toJSONString();
    }
    
    @RequestMapping(value="/reservations/{reservationId}", 
    		method = RequestMethod.GET)
    public String getReservation(@PathVariable(value="reservationId") String reservationId){
    	JSONObject json = new JSONObject();
    	try{
    		reservationModel = reservationFacade.getResv(reservationId);
    		if(reservationModel != null)
    		{
    			json.put("reservationID", reservationModel.getReservationId());
            	json.put("phoneNumber", reservationModel.getPhoneNumber());
            	json.put("email", reservationModel.getEmail());
            	json.put("parkingLotID", reservationModel.getParkingLotID());
            	json.put("carSize", reservationModel.getCarSize());
            	json.put("reservationTime", reservationModel.getReservationTime().toString());
            	if(reservationModel.getEntranceTime() ==null)
            	{
            		json.put("entranceTime", "null");
            	}else
            	{
            		json.put("entranceTime", reservationModel.getEntranceTime());
            	}
            	if(reservationModel.getExitTime() ==null)
            	{
            		json.put("exitTime", "null");
            	}else
            	{
            		json.put("exitTime", reservationModel.getExitTime());
            	}
            	
            	System.out.println(json.toJSONString());
    		}else
    		{
    			json.put("result", "fail");
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	if(json.size() != 0){
    		return json.toJSONString();
    	}else{
    		return null;
    	}
    }
    
    @RequestMapping(value="/reservations/{reservationId}", 
    		method = RequestMethod.DELETE)
    public String deleteReservation(@PathVariable(value="reservationId") String reservationId,@RequestBody JSONObject jsonO){
    	JSONObject result = new JSONObject();
    	try{
    		reservationFacade.deleteResv(reservationId);

    		if(jsonO.containsKey("phoneNumber"))
    		{
    			try{
    				driverFacade.updateDriverState(jsonO.get("phoneNumber").toString(), DriverModel.UNRESERVED);
    			}catch(DataAccessException e)
    			{
    				e.printStackTrace();
    			}
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		result.put("result", "fail");
    		return result.toJSONString();
    	}
    	result.put("result", "success");
    	return result.toJSONString();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
