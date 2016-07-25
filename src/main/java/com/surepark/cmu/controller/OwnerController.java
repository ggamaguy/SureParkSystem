package com.surepark.cmu.controller;

import java.io.IOException;
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
