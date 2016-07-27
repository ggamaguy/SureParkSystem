package com.surepark.cmu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.geo.Point;

import com.surepark.cmu.domains.ParkingLotModel;

public interface ParkingLotInterface {
	

	public void updateParkingLot(ParkingLotModel pm) throws DataAccessException;
	public void deleteParkingLot(String parkingLotId) throws DataAccessException;
	public void insertParkingLot(ParkingLotModel pm) throws DataAccessException;
	public List<ParkingLotModel> selectParkingLotsByArea(String cityName) throws DataAccessException;
	public ParkingLotModel selectParkingLotByParkingLotId(String parkingLotId) throws DataAccessException;
	public List<ParkingLotModel> selectParkingLotByOwnerId(String ownerId) throws DataAccessException;
	public String selectParkingLotIP(String parkingLotId) throws DataAccessException;
	
}
