package controllers;

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

import domains.ReservationModel;
import interfaces.IRReservation;

@RestController
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReservationModel reservationModel;
    @Autowired
    private IRReservation reservationFacade;
    
    public ReservationController() {
        super();

    }
    @RequestMapping(value="/Reservation", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public boolean makeReservation(@RequestBody JSONObject jsonO ){
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
