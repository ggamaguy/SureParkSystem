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

import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.interfaces.ReservationInterface;

@RestController
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private ReservationModel reservationModel;
    
    @Autowired
    private ReservationInterface reservationFacade;
    
    public ReservationController() {
        super();

    }
    
    @RequestMapping(value="/Reservation", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public boolean makeReservation(@RequestBody JSONObject jsonO ){
    	if(jsonO.containsKey("phoneNumber")){
    		
    	}
    	return true;
    }
    @RequestMapping(value="/Reservation", 
    		method = RequestMethod.GET,
    		consumes="application/json")
    public boolean getReservation(@RequestBody JSONObject jsonO ){
    	return true;
    }
    @RequestMapping(value="/Reservation", 
    		method = RequestMethod.DELETE,
    		consumes="application/json")
    public boolean deleteReservation(@RequestBody JSONObject jsonO ){
    	return true;
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
