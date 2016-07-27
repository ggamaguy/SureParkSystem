package com.surepark.cmu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.surepark.cmu.domains.OwnerModel;
import com.surepark.cmu.domains.ParkingLotModel;
import com.surepark.cmu.facades.OwnerFacade;
import com.surepark.cmu.facades.ParkingLotFacade;
import com.surepark.cmu.facades.ParkingLotStatisticFacade;
import com.surepark.cmu.facades.ParkingLotStatusFacade;

/**
 * Servlet implementation class OwnerController
 */
@RestController
@SessionAttributes("loginState")
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

	@RequestMapping(value = "/owners/login", method = RequestMethod.POST, consumes = "application/json")
	public String LoginOwner(@RequestBody JSONObject jsonO, @ModelAttribute HashMap<String, String> loginState) {
		JSONObject jsonroot = new JSONObject();
		OwnerModel owner = null;
		try {

			if (jsonO.containsKey("ownerID") && jsonO.containsKey("ownerPassword")) {
				String ownerID = jsonO.get("ownerID").toString();
				String ownerPassword = jsonO.get("ownerPassword").toString();
				owner = ownerFacade.loginOwner(ownerID, ownerPassword);
				if (owner == null) {
					jsonroot.put("result", "fail");
				} else if (owner.getOwnerID().equals(ownerID)) {
					String ownerTwofactorPassword = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
					try {
						ownerFacade.updateOwnerSecondPassword(ownerID, ownerTwofactorPassword);

						/*
						 * try { sendMail(owner.getOwnerName(),
						 * owner.getOwnerEmail(), "Two Factor Password : "
						 * +ownerTwofactorPassword); } catch
						 * (UnsupportedEncodingException | MessagingException e)
						 * { // TODO Auto-generated catch block
						 * e.printStackTrace(); }
						 */

					} catch (DataAccessException e) {
						e.printStackTrace();
						jsonroot.put("result", "fail");
						loginState.put("Success1stLogIn", "false");
					}
					jsonroot.put("result", "sucess");
					loginState.put("Success1stLogIn", "success");
				}
			} else {
				jsonroot.put("result", "fail");
				loginState.put("Success1stLogIn", "false");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			jsonroot.put("result", "fail");
			loginState.put("Success1stLogIn", "false");
		}

		return jsonroot.toJSONString();
	}

	@RequestMapping(value = "/owners/login/second", method = RequestMethod.POST, consumes = "application/json")
	public String LoginOwnerSecondFactor(@RequestBody JSONObject jsonO,
			@ModelAttribute HashMap<String, String> loginState) {
		JSONObject jsonroot = new JSONObject();
		OwnerModel owner = null;
		if (loginState.get("Success1stLogIn").equalsIgnoreCase("true")) {
			try {
				if (jsonO.containsKey("ownerID") && jsonO.containsKey("ownerTwofactorPassword")) {
					String ownerID = jsonO.get("ownerID").toString();
					String ownerSecondPassword = jsonO.get("ownerTwofactorPassword").toString();
					System.out.println(jsonO.toJSONString());
					owner = ownerFacade.loginOwnerSecondPassword(ownerID, ownerSecondPassword);
					System.out.println(owner.toString());
					if (owner == null) {
						jsonroot.put("result", "fail");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success2ndLogin", "false");
					} else if (owner.getOwnerID().equals(ownerID)) {
						jsonroot.put("result", "sucess");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success2ndLogin", "true");
						loginState.put("ownerId", ownerID);
					} else {
						jsonroot.put("result", "fail");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success2ndLogin", "false");
					}
				} else {
					jsonroot.put("result", "fail");
					loginState.put("Success2ndLogin", "false");
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				jsonroot.put("result", "fail");
				loginState.put("Success2ndLogin", "false");
			}
		} else {

		}

		return jsonroot.toJSONString();
	}

	@RequestMapping(value = "/owner/{ownerId}", method = RequestMethod.GET)
	public String getOwner(@PathVariable(value = "ownerId") String ownerId,
			@ModelAttribute HashMap<String, String> loginState) {
		JSONObject result = new JSONObject();
		OwnerModel owner = new OwnerModel();
		if ((loginState.containsKey("Success1stLogIn") && loginState.containsKey("Success2ndLogIn")
				&& loginState.containsKey("ownerId"))
				&& (loginState.get("Success1stLogIn").equalsIgnoreCase("true")
						&& loginState.get("Success2ndLogIn").equalsIgnoreCase("true")
						&& loginState.get("ownerId").equalsIgnoreCase(ownerId))) {
			try {
				owner = ownerFacade.findOwner(ownerId);
				result.put("ownerID", owner.getOwnerID());
				result.put("ownerName", owner.getOwnerName());
				result.put("ownerEmail", owner.getOwnerEmail());
				result.put("ownerPhoneNumber", owner.getOwnerPhoneNumber());
			} catch (Exception e) {
				e.printStackTrace();
				result.put("result", "fail");
				return result.toJSONString();
			}
		} else {
			result.put("result", "fail");
		}
		return result.toJSONString();
	}

	@RequestMapping(value = "/owner/{ownerId}/parkinglots", method = RequestMethod.GET)
	public String getAllParkingLots(@PathVariable(value = "ownerId") String ownerId,
			@ModelAttribute HashMap<String, String> loginState) {
		JSONObject result = new JSONObject();
		JSONArray parkingLotJSONArray = new JSONArray();
		List<ParkingLotModel> parkingLotList = new ArrayList<>();
		if ((loginState.containsKey("Success1stLogin") && loginState.containsKey("Success2ndLogin")
				&& loginState.containsKey("ownerId"))
				&& (loginState.get("Success1stLogin").equalsIgnoreCase("true")
						&& loginState.get("Success2ndLogin").equalsIgnoreCase("true")
						&& loginState.get("ownerId").equalsIgnoreCase(ownerId))) {
			try {
				parkingLotList = parkingLotFacade.selectParkingLotByOwnerId(ownerId);
				result.put("count", parkingLotList.size());
				for (ParkingLotModel model : parkingLotList) {
					JSONObject temp = new JSONObject();
					temp.put("parkingLotID", model.getParkingLotID());
					temp.put("parkingLotName", model.getParkingLotName());
					temp.put("ParkingLotLocationLatitude", model.getParkingLotLocationLatitude());
					temp.put("ParkingLotLocationLongitude", model.getParkingLotLocationLongitude());
					temp.put("parkingLotAdress", model.getParkingLotAdress());
					temp.put("parkingLotStartTime", model.getParkingLotStartTime());
					temp.put("parkingLotEndTime", model.getParkingLotEndTime());
					temp.put("parkingLotMaximumCapacity", model.getParkingLotMaximumCapacity());
					temp.put("ownerID", model.getOwnerID());
					temp.put("parkingLotGracePeriod", model.getParkingLotGracePeriod());
					temp.put("parkingLotPreResvationPeriod", model.getParkingLotPreResvationPeriod());
					parkingLotJSONArray.add(temp);
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.clear();
				result.put("result", "fail");
				return result.toJSONString();
			}
		}
		result.put("parkinglot", parkingLotJSONArray);
		return result.toJSONString();
	}

	@RequestMapping(value="/owner/{ownerId}/parkinglots/{parkinglotId}",
    		method = RequestMethod.PUT,
    		consumes = "application/json")
    public String updateParkingLot(@PathVariable(value="ownerId")String ownerId, 
    		@PathVariable(value="parkinglotId") String parkinglotId,
    		@RequestBody JSONObject json,
    		@ModelAttribute HashMap<String,String> loginState){
    	JSONObject result = new JSONObject();
    	ParkingLotModel parkingLot = new ParkingLotModel();
    	if((loginState.containsKey("Success1stLogin")
    			&& loginState.containsKey("Success2ndLogin")
    			&& loginState.containsKey("ownerId")
    			)&&(loginState.get("Success1stLogin").equalsIgnoreCase("true")
    			&& loginState.get("Success2ndLogin").equalsIgnoreCase("true")
    			&& loginState.get("ownerId").equalsIgnoreCase(ownerId))){
    		try{
    			if(json.containsKey("parkingLotID") && json.containsKey("parkingLotName") 
    					&& json.containsKey("parkingLotLocationLongitude") && json.containsKey("parkingLotLocationLatitude") 
    					&& json.containsKey("parkingLotAdress") && json.containsKey("parkingLotStartTime") && json.containsKey("parkingLotEndTime") 
    					&& json.containsKey("parkingLotMaximumCapacity") && json.containsKey("ownerID") 
    					&& json.containsKey("parkingLotGracePeriod") && json.containsKey("parkingLotPreResvationPeriod ")){
    				parkingLot.setParkingLotID(json.get("parkingLotID").toString());
    				parkingLot.setParkingLotName(json.get("parkingLotName").toString());
    				parkingLot.setParkingLotLocationLongitude(json.get("parkingLotLocationLongitude").toString());
    				parkingLot.setParkingLotLocationLatitude(json.get("parkingLotLocationLatitude").toString());
    				parkingLot.setParkingLotAdress(json.get("parkingLotAdress").toString());
    				parkingLot.setParkingLotStartTime(json.get("parkingLotStartTime").toString());
    				parkingLot.setParkingLotEndTime(json.get("parkingLotEndTime").toString());
    				parkingLot.setParkingLotMaximumCapacity(json.get("parkingLotMaximumCapacity").toString());
    				parkingLot.setOwnerID(json.get("ownerID").toString());
    				parkingLot.setParkingLotGracePeriod(json.get("parkingLotGracePeriod").toString());
    				parkingLot.setParkingLotPreResvationPeriod(json.get("parkingLotPreResvationPeriod ").toString());
    				parkingLotFacade.updateParkingLot(parkingLot);
    				result.put("result", "success");
    			}
    		}catch(Exception e){
    			e.printStackTrace();
    			result.put("result", "fail");
    			return result.toJSONString();
    		}
    	}else{
    		result.put("result", "fail");
    	}
    	return result.toJSONString();
	}

	private void sendMail(String ownerName, String ownerEmail, String Text) throws UnsupportedEncodingException, MessagingException
    {
    	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    	mailSender.setHost("smtp.gmail.com");
    	mailSender.setPort(587);
    	mailSender.setUsername("chamoonsu@gmail.com");
    	mailSender.setPassword("asdzxc12!");
    
    	SimpleMailMessage msg = new SimpleMailMessage();
    	msg.setFrom("chamoonsu@gmail.com");
    	msg.setTo(new String[] {ownerName,ownerEmail});
    	msg.setSubject("Sure Park Two Factor Password");
    	msg.setText(Text);
    	 
    	try {
    	    mailSender.send(msg);
    	} catch (MailException ex) {
    	    ex.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
