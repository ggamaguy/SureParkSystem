package com.surepark.cmu.controller;

import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.domains.ParkingLotModel;
import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.interfaces.ParkingLotInterface;
import com.surepark.cmu.interfaces.ReservationInterface;

/**
 * Servlet implementation class LocalSurePark
 */
@RestController
public class LocalSurePark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
	ReservationInterface reservationFacade;
	@Autowired
	ParkingLotInterface parkingLotFacade;

	public LocalSurePark() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * @RequestMapping(value="/sureparks/ping/{parkingLotId}", method =
	 * RequestMethod.GET) public String
	 * localSureParkPingEcho(@PathVariable(value="parkingLotId") String
	 * parkingLotId){ JSONObject result = new JSONObject(); result.put("status",
	 * "alive"); return result.toJSONString(); }
>>>>>>> reservation
	 */
	@RequestMapping(value = "/sureparks/list/{cityName}",
			method = RequestMethod.GET)
	public String getParkingLotList(@PathVariable(value="cityName") String cityName){
		JSONObject result = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<ParkingLotModel> parkingLotList = parkingLotFacade.selectParkingLotsByArea(cityName);
		try{
			for(int idx = 0; idx < parkingLotList.size();idx++){
				ParkingLotModel temp = parkingLotList.get(idx);
				JSONObject obj = new JSONObject();
				obj.put("parkingLotID", temp.getParkingLotID());
				obj.put("parkingLotName", temp.getParkingLotName());
				obj.put("parkingLotLocationLatitude", temp.getParkingLotLocationLatitude());
				obj.put("parkingLotLocationLongitude", temp.getParkingLotLocationLongitude());
				obj.put("parkingLotAdress", temp.getParkingLotAdress());
				obj.put("parkingLotStartTime", temp.getParkingLotStartTime());
				obj.put("parkingLotEndTime", temp.getParkingLotEndTime());
				obj.put("parkingLotMaximumCapacity", temp.getParkingLotMaximumCapacity());
				obj.put("parkingLotGracePeriod", temp.getParkingLotGracePeriod());
				obj.put("parkingLotPreResvationPeriod", temp.getParkingLotPreResvationPeriod());
				jsonArray.add(obj);
				result.put("count", idx+1);
			}
			result.put("sureparks", jsonArray);
		}catch(Exception e){
			e.printStackTrace();
			result.clear();
			result.put("result", "fail");
		}
		return result.toJSONString();
	}

	@RequestMapping(value = "/sureparks/sync/{parkingLotId}", 
			method = RequestMethod.PUT, 
			consumes = "application/json")
	public String syncWithLocalServer(@RequestBody JSONObject json) {
		int count = 0;
		JSONObject result = new JSONObject();
		JSONArray reservationList = new JSONArray();
		ArrayList<ReservationModel> reservationModelList = new ArrayList<>();
		try {
			if (json.containsKey("count") && json.get("count") != null) {
				count = Integer.parseInt(json.get("count").toString());
			}
			reservationList = (JSONArray) json.get("reservations");
			if (count == reservationList.size()) {
				for (int idx = 0; idx < reservationList.size(); idx++) {
					ReservationModel tempReservationModel = new ReservationModel();
					JSONObject element = (JSONObject) reservationList.get(idx);
					tempReservationModel.setReservationId(Integer.parseInt(element.get("reservationID").toString()));
					tempReservationModel.setPhoneNumber(element.get("phoneNumber").toString());
					tempReservationModel.setEmail(element.get("email").toString());
					tempReservationModel.setParkingLotID(element.get("parkingLotID").toString());
					tempReservationModel.setCarSize(Integer.parseInt(element.get("carSize").toString()));
					tempReservationModel
							.setReservationTime(Timestamp.valueOf(element.get("reservationTime").toString()));
					tempReservationModel.setReservationTime(Timestamp.valueOf(element.get("entranceTime").toString()));
					tempReservationModel.setReservationTime(Timestamp.valueOf(element.get("exitTime").toString()));
					reservationModelList.add(tempReservationModel);
				}
			} else {
				result.put("result", "fail");
				return result.toJSONString();
			}
			for (int idx = 0; idx < reservationModelList.size(); idx++) {
				reservationFacade.updateResv(reservationModelList.get(idx));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
			return result.toJSONString();
		}
		result.put("result", "success");
		return result.toJSONString();
	}

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
