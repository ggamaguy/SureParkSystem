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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/owner/login", method = RequestMethod.POST, consumes = "application/json")
	public String LoginOwner(@RequestBody JSONObject jsonO, HttpServletRequest request) {
		JSONObject jsonroot = new JSONObject();
		OwnerModel owner = null;
		HttpSession session = request.getSession();
		HashMap<String,String> loginState = new HashMap<String,String>();
		if(session.getAttribute("loginState") != null){
			loginState = ((HashMap<String,String>)session.getAttribute("loginState"));
		}
		try {
			if (jsonO.containsKey("ownerID") && jsonO.containsKey("ownerPassword")) {
				String ownerID = jsonO.get("ownerID").toString();
				String ownerPassword = jsonO.get("ownerPassword").toString();
				owner = ownerFacade.loginOwner(ownerID, ownerPassword);
				if (owner == null || owner.getOwnerAccountAvailable() == "false") {
					jsonroot.put("result", "fail");
					loginState.put("Success1stLogin", "false");
					OwnerModel tryModel = ownerFacade.getOwnerAvailable(ownerID);
					System.out.println(ownerID);
					int cnt = Integer.parseInt(tryModel.getOwner1stLoginTry());
					cnt++;
					tryModel.setOwner1stLoginTry(String.valueOf(cnt));
					System.out.println(tryModel.getOwner1stLoginTry());
					if(Integer.valueOf(tryModel.getOwner1stLoginTry()) >= 3){
						tryModel.setOwnerAccountAvailable("false");
						jsonroot.put("result", "unavailable");
					}
					ownerFacade.updateOwnerAvailable(ownerID, tryModel.getOwner1stLoginTry(), tryModel.getOwner2ndLoginTry(), tryModel.getOwnerAccountAvailable());
				
				} else if (owner.getOwnerID().equals(ownerID)) {
					OwnerModel tryModel = ownerFacade.getOwnerAvailable(ownerID);
					tryModel.setOwner1stLoginTry("0");
					ownerFacade.updateOwnerAvailable(ownerID, tryModel.getOwner1stLoginTry(), tryModel.getOwner2ndLoginTry(), tryModel.getOwnerAccountAvailable());
					String ownerSecondPassword = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
					try {
						//ownerFacade.updateOwnerSecondPassword(ownerID, ownerSecondPassword);

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
					loginState.put("Success1stLogIn", "true");
					session.setAttribute("loginState", loginState);
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

	@RequestMapping(value = "/owner/login/second", method = RequestMethod.POST, consumes = "application/json")
	public String LoginOwnerSecondFactor(@RequestBody JSONObject jsonO,
			HttpServletRequest request) {
		JSONObject jsonroot = new JSONObject();
		OwnerModel owner = null;
		HttpSession session = request.getSession();
		HashMap<String,String> loginState = new HashMap<String,String>();
		if(session.getAttribute("loginState") != null){
			System.out.println("1");
			loginState = ((HashMap<String,String>)session.getAttribute("loginState"));
		}else{
			System.out.println("2");
			jsonroot.put("result", "fail");
			return jsonroot.toJSONString();
		}
		if (loginState.get("Success1stLogIn").equalsIgnoreCase("true")) {
			System.out.println("3");
			try {
				if (jsonO.containsKey("ownerID") && jsonO.containsKey("ownerSecondPassword")) {
					System.out.println("4");
					String ownerID = jsonO.get("ownerID").toString();
					String ownerSecondPassword = jsonO.get("ownerSecondPassword").toString();
					//System.out.println(jsonO.toJSONString());
					owner = ownerFacade.loginOwnerSecondPassword(ownerID, ownerSecondPassword);
					//System.out.println(owner.toString());
					if (owner == null){
					System.out.println("5");
						jsonroot.put("result", "fail");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success1stLogin", "true");
						loginState.put("Success2ndLogin", "false");
						OwnerModel tryModel = ownerFacade.getOwnerAvailable(ownerID);
						int cnt = Integer.parseInt(tryModel.getOwner2ndLoginTry());
						cnt++;
						tryModel.setOwner2ndLoginTry(String.valueOf(cnt));
						if(Integer.valueOf(tryModel.getOwner2ndLoginTry())>=3){
							tryModel.setOwnerAccountAvailable("false");
							jsonroot.put("result", "unavailable");
						}
						ownerFacade.updateOwnerAvailable(ownerID, tryModel.getOwner1stLoginTry(), tryModel.getOwner2ndLoginTry(), tryModel.getOwnerAccountAvailable());
					} else if (owner.getOwnerID().equals(ownerID)){
						OwnerModel tryModel = ownerFacade.getOwnerAvailable(ownerID);
						tryModel.setOwner2ndLoginTry("0");
						ownerFacade.updateOwnerAvailable(ownerID, tryModel.getOwner1stLoginTry(), tryModel.getOwner2ndLoginTry(), tryModel.getOwnerAccountAvailable());
						jsonroot.put("result", "sucess");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success1stLogin", "true");
						loginState.put("Success2ndLogin", "true");
						loginState.put("ownerId", ownerID);
						//session.setAttribute("loginState", loginState);
					} else {
						System.out.println("7");
						jsonroot.put("result", "fail");
						ownerFacade.updateOwnerSecondPassword(ownerID, "");
						loginState.put("Success1stLogin", "true");
						loginState.put("Success2ndLogin", "false");
					}
				} else {
					System.out.println("8");
					jsonroot.put("result", "fail");
					loginState.put("Success1stLogin", "true");
					loginState.put("Success2ndLogin", "false");
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
				jsonroot.put("result", "fail");
				loginState.put("Success1stLogin", "true");
				loginState.put("Success2ndLogin", "false");
			}
		} else {
			System.out.println("9");
			jsonroot.put("result", "fail");
			session.setAttribute("loginState", loginState);
			return jsonroot.toJSONString();
		}
		System.out.println("10");
		session.setAttribute("loginState", loginState);
		return jsonroot.toJSONString();
	}

	@RequestMapping(value = "/owner/{ownerId}", method = RequestMethod.GET)
	public String getOwner(@PathVariable(value = "ownerId") String ownerId,
			HttpServletRequest request) {
		JSONObject result = new JSONObject();
		OwnerModel owner = new OwnerModel();
		HttpSession session = request.getSession();
		HashMap<String,String> loginState = new HashMap<String,String>();
		if(session.getAttribute("loginState") != null){
			loginState = ((HashMap<String,String>)session.getAttribute("loginState"));
		}else{
			result.put("result", "fail");
			return result.toJSONString();
		}
		
		if ((loginState.containsKey("Success1stLogin") && loginState.containsKey("Success2ndLogin")
				&& loginState.containsKey("ownerId"))
				&& (loginState.get("Success1stLogin").equalsIgnoreCase("true")
						&& loginState.get("Success2ndLogin").equalsIgnoreCase("true")
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
	public String getAllParkingLots(@PathVariable(value = "ownerId") String ownerId, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		JSONArray parkingLotJSONArray = new JSONArray();
		List<ParkingLotModel> parkingLotList = new ArrayList<>();
		HttpSession session = request.getSession();
		HashMap<String,String> loginState = new HashMap<String,String>();
		if(session.getAttribute("loginState") != null){
			loginState = ((HashMap<String,String>)session.getAttribute("loginState"));
		}else{
			result.put("result", "fail");
			return result.toJSONString();
		}

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
    		HttpServletRequest request){
    	JSONObject result = new JSONObject();
    	ParkingLotModel parkingLot = new ParkingLotModel();
    	HttpSession session = request.getSession();
		HashMap<String,String> loginState = new HashMap<String,String>();
		if(session.getAttribute("loginState") != null){
			loginState = ((HashMap<String,String>)session.getAttribute("loginState"));
		}else{
			result.put("result", "fail");
			return result.toJSONString();
		}
		System.out.println(loginState.containsKey("Success1stLogin"));
		System.out.println(loginState.containsKey("Success2ndLogin")); 
		System.out.println(loginState.containsKey("ownerId"));
		System.out.println(loginState.get("Success1stLogin").equalsIgnoreCase("true"));
		System.out.println(loginState.get("Success2ndLogin").equalsIgnoreCase("true")); 
		System.out.println(loginState.get("ownerId").equalsIgnoreCase(ownerId));		
		for(int i = 0; i < json.keySet().toArray().length;i++){
			System.out.println(json.keySet().toArray()[i]);
		}
		
    	if((loginState.containsKey("Success1stLogin")
    			&& loginState.containsKey("Success2ndLogin")
    			&& loginState.containsKey("ownerId")
    			)&&(loginState.get("Success1stLogin").equalsIgnoreCase("true")
    			&& loginState.get("Success2ndLogin").equalsIgnoreCase("true")
    			&& loginState.get("ownerId").equalsIgnoreCase(ownerId))){
    		System.out.println("1");
    		System.out.println(json.containsKey("parkingLotID"));
    		System.out.println(json.containsKey("parkingLotName"));
    		System.out.println(json.containsKey("parkingLotLocationLongitude"));
    		System.out.println(json.containsKey("parkingLotLocationLatitude"));
    		System.out.println(json.containsKey("parkingLotAdress"));
    		System.out.println(json.containsKey("parkingLotMaximumCapacity"));
    		System.out.println(json.containsKey("ownerID"));
    		System.out.println(json.containsKey("parkingLotGracePeriod"));
    		System.out.println(json.containsKey("parkingLotPreResvationPeriod"));
    		System.out.println(json.containsKey("parkingLotName"));
    		System.out.println(json.containsKey("parkingLotStartTime"));
    		System.out.println(json.containsKey("parkingLotEndTime"));
    		try{
    			if(json.containsKey("parkingLotID") && json.containsKey("parkingLotName") 
    					&& json.containsKey("parkingLotLocationLongitude") && json.containsKey("parkingLotLocationLatitude") && json.containsKey("parkingLotAdress") 
    					&& json.containsKey("parkingLotStartTime") && json.containsKey("parkingLotEndTime") 
    					&& json.containsKey("parkingLotMaximumCapacity") && json.containsKey("ownerID") 
    					&& json.containsKey("parkingLotGracePeriod") && json.containsKey("parkingLotPreResvationPeriod")){
    				System.out.println("2");
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
    				parkingLot.setParkingLotPreResvationPeriod(json.get("parkingLotPreResvationPeriod").toString());
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
