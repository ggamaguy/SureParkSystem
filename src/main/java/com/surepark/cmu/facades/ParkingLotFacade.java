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
	public ParkingLotModel selectParkingLotByParkingLotId(String parkingLotId) throws DataAccessException {
		return sqlSession.selectOne("ParkingLotFacade.selectParkingLotByParkingLotId", parkingLotId);
	}

	@Override
	public String selectParkingLotIP(String parkingLotID) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ParkingLotFacade.selectParkingLotIP", parkingLotID);
	}

	@Override
	public List<ParkingLotModel> selectParkingLotByOwnerId(String ownerId) throws DataAccessException {
		return sqlSession.selectList("ParkingLotFacade.selectParkingLotByOwnerId",ownerId);
	}
	 	
}
