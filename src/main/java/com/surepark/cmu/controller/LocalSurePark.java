package com.surepark.cmu.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.interfaces.ReservationInterface;

/**
 * Servlet implementation class LocalSurePark
 */
@RestController
public class LocalSurePark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Autowired
    ReservationInterface reservationFacade;
    
    public LocalSurePark() {
        super();
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value="/sureparks/ping/{parkingLotId}", 
    		method = RequestMethod.GET)
    public String localSureParkPingEcho(@PathVariable(value="parkingLotId") String parkingLotId){
    	JSONObject result = new JSONObject();
    	result.put("status", "alive");
    	return result.toJSONString();
    }
    @RequestMapping(value="/sureparks/ping/{parkingLotId}", 
    		method = RequestMethod.PUT,
    		consumes = "application/json")
    public String syncWithLocalServer(@RequestBody JSONObject json){
    	JSONObject result = new JSONObject();
    
    	return result.toJSONString();
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
