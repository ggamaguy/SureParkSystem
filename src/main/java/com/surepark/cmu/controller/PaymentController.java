package com.surepark.cmu.controller;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.surepark.cmu.domains.CardModel;
import com.surepark.cmu.domains.CustomerMongoModel;
import com.surepark.cmu.domains.ParkingLotModel;
import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.facades.DriverFacade;
import com.surepark.cmu.facades.ParkingLotFacade;
import com.surepark.cmu.facades.ReservationFacade;
import com.surepark.cmu.mongorepository.CustomerMongoRepository;
import com.surepark.cmu.payment.PaymentAPI;

@RestController
public class PaymentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final long DEFAULT_CAL_TIME = 10;
	private static final double DEFAULT_CAL_MONEY = 2.0;
	

	@Autowired
	private DriverFacade driverFacade;
	
	@Autowired
	private ReservationFacade reservationFacade;
	
	@Autowired
	private CustomerMongoRepository customerMongoRepository;
	
	@Autowired
	private ParkingLotFacade parkingLotFacade;
	
	@Autowired
	private PaymentAPI paymentAPI;
	

	@RequestMapping(value="/payment/{phoneNumber}/{reservationID}", 
    		method = RequestMethod.POST,
    		consumes="application/json")
    public String RegisterDriver(@PathVariable(value="phoneNumber") String phoneNumber,@PathVariable(value="reservationId") String reservationId) throws IOException, ParseException{
    	
    	JSONObject jsonroot=new JSONObject();
    	
    	ReservationModel reservationModel = reservationFacade.getResv(phoneNumber, reservationId);
    	
    	double money = calculateMoney(reservationModel.getEntranceTime().toString(), reservationModel.getExitTime().toString());
    	
    	
		String cardNumber = reservationModel.getCardNumber();
		String cardExpirationMonth = reservationModel.getCardExpirationMonth();
		String cardExpirationYear = reservationModel.getCardExpirationYear();
		String cardValidationCode = reservationModel.getCardValidationCode();
		String cardHolder = reservationModel.getCardHolder();
		
		CardModel card = new CardModel(phoneNumber, cardNumber, cardExpirationMonth, cardExpirationYear, cardValidationCode, cardHolder);
		
		System.out.println(card.toString());
		boolean result = paymentAPI.payment(card , money);
    	
		if(result)
		{
			boolean sendmailResult = sendPaymentEmail(reservationModel.getEmail(),money);
			
			if(sendmailResult)
			{
				ParkingLotModel parkingLotModel = parkingLotFacade.selectParkingLotByParkingLotId(reservationModel.getParkingLotID());
				
				CustomerMongoModel customer = new CustomerMongoModel();
				
				customer.setCarSize(reservationModel.getCarSize());
				customer.setEntranceTime(reservationModel.getEntranceTime());
				customer.setExitTime(reservationModel.getExitTime());
				customer.setPhoneNumber(reservationModel.getPhoneNumber());
				customer.setReservationTime(reservationModel.getReservationTime());
				customer.setEmail(reservationModel.getEmail());
				customer.setReservationId(reservationModel.getReservationId());
				customer.setOwnerID(parkingLotModel.getOwnerID());
				customer.setParkingLotAdress(parkingLotModel.getParkingLotAdress());
				customer.setParkingLotEndTime(parkingLotModel.getParkingLotEndTime());
				customer.setParkingLotGracePeriod(parkingLotModel.getParkingLotGracePeriod());
				customer.setParkingLotID(parkingLotModel.getParkingLotID());
				customer.setParkingLotLocationLatitude(parkingLotModel.getParkingLotLocationLatitude());
				customer.setParkingLotLocationLongitude(parkingLotModel.getParkingLotLocationLongitude());
				customer.setParkingLotMaximumCapacity(parkingLotModel.getParkingLotMaximumCapacity());
				customer.setParkingLotName(parkingLotModel.getParkingLotName());
				customer.setParkingLotPreResvationPeriod(parkingLotModel.getParkingLotPreResvationPeriod());
				customer.setParkingLotStartTime(parkingLotModel.getParkingLotStartTime());
				
				customerMongoRepository.save(customer);
				
				driverFacade.deleteDriver(phoneNumber);
				
				
				jsonroot.put("result", "success");
				
			}else
			{
				// 메일 보내기 실패
			}
			
			
		}else
		{
			jsonroot.put("result", "fail");
		}
    	
    	
    	return jsonroot.toJSONString();
    }
	
	
	
	private boolean sendPaymentEmail(String email, double money)
	{
		boolean result = true; 
		
		return result; 
	}
	
	private double calculateMoney(String entranceTime, String exitTime)
	{
		long diffTime = diffTimes(entranceTime,exitTime);
		
		double money = 0.0;
		
		if(diffTime >= 10)
		{
			money = ( (int)( diffTime / DEFAULT_CAL_TIME ) + 1.0 ) * DEFAULT_CAL_MONEY;
			
		}else if(diffTime < 10)
		{
			money = 0.;
		}else
		{
			//
		}
		
		return money;
		
	}
	
	private long diffTimes(String entranceTime, String exitTime)
	{
		String start= entranceTime;
		Calendar tempcal=Calendar.getInstance();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date startday=sf.parse(start, new ParsePosition(0));

		long startTime=startday.getTime();

		String end= exitTime;
		tempcal=Calendar.getInstance();
		sf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date endday=sf.parse(end, new ParsePosition(0));
		
		long endTime=endday.getTime();
			
		long mills=endTime-startTime;
			
		long min=mills/60000;
		
		System.out.println(min +" min.");
		
		return min;
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
