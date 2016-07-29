package com.surepark.cmu.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.ParkingLotModel;

public interface ParkingLotInterface {
	

	public void updateParkingLot(ParkingLotModel pm) throws DataAccessException;
	public void deleteParkingLot(String parkingLotID) throws DataAccessException;
	public void insertParkingLot(ParkingLotModel pm) throws DataAccessException;
	public List<ParkingLotModel> selectParkingLotsByArea(String cityName) throws DataAccessException;

	public List<ParkingLotModel> selectParkingLotByOwnerId(String ownerId) throws DataAccessException;
	

	public ParkingLotModel selectParkingLotByParkingLotId(String parkingLotID) throws DataAccessException;
	public String selectParkingLotIP(String parkingLotID) throws DataAccessException;

	
}
