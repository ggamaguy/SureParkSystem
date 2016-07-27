package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

public interface ParkingLotStatusInterface {
	
	public void updateParkingLotState(String parkingLotID, String parkingLotState) throws DataAccessException;
	
	public void increaseAvaliableParkingSpot(String parkingLotID) throws DataAccessException;
	public void decreaseAvaliableParkingSpot(String parkingLotID) throws DataAccessException;
	

}
