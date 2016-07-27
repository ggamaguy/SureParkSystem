package com.surepark.cmu.facades;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.interfaces.ParkingLotStatusInterface;

@Service
public class ParkingLotStatusFacade implements ParkingLotStatusInterface{

	@Autowired
	SqlSession sqlSession;

	
	
	@Override
	public void updateParkingLotState(String parkingLotID, String parkingLotState) throws DataAccessException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("parkingLotID", parkingLotID);
		map.put("parkingLotState", parkingLotState);
		this.sqlSession.update("ParkingLotStatusFacade.updateParkingLotState", map);
	}

	@Override
	public void increaseAvaliableParkingSpot(String parkingLotID) throws DataAccessException {
		this.sqlSession.update("ParkingLotStatusFacade.increaseAvaliableParkingSpot", parkingLotID);
		
	}

	@Override
	public void decreaseAvaliableParkingSpot(String parkingLotID) throws DataAccessException {

		this.sqlSession.update("ParkingLotStatusFacade.decreaseAvaliableParkingSpot", parkingLotID);
		
	}

	
	

}
