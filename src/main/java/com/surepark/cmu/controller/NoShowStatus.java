package com.surepark.cmu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.surepark.cmu.interfaces.ReservationInterface;

/**
 * Servlet implementation class NoShowStatus
 */
public class NoShowStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	ReservationInterface reservationFacade;
	@Autowired
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoShowStatus() {
        super();
        // TODO Auto-generated constructor stub
    }
    @RequestMapping(value="/noshow/{phoneNumber}", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String deliveredNoshowStatus(@PathVariable(value="phoneNumber") String reservationId){
    	
    	return null;
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
