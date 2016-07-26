package com.surepark.cmu.facades;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.ParkingLotModel;
import com.surepark.cmu.interfaces.ParkingLotInterface;

@Service
public class ParkingLotFacade implements ParkingLotInterface{

	@Autowired
	SqlSession sqlSession;
	@Override
	public void updateParkingLot(ParkingLotModel pm) throws DataAccessException {
		sqlSession.update("ParkingLotFacade.updateParkingLot",pm);
	}

	@Override
	public void deleteParkingLot(String parkingLotId) throws DataAccessException {
		sqlSession.delete("ParkingLotFacade.updateParkingLot",parkingLotId);
	}

	@Override
	public void insertParkingLot(ParkingLotModel pm) throws DataAccessException {
		sqlSession.insert("ParkingLotFacade.insertParkingLot",pm);
	}

	@Override
	public List<ParkingLotModel> selectParkingLotsByArea(String cityName) throws DataAccessException {
		return sqlSession.selectList("ParkingLotFacade.selectParkingLotsByArea", cityName);
	}

	@Override
	public List<ParkingLotModel> selectParkingLotByParkingLotId(String parkingLotId) throws DataAccessException {
		return sqlSession.selectList("ParkingLotFacade.selectParkingLotByParkingLotId", parkingLotId);
	}
	 	
}
