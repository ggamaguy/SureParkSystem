package com.surepark.cmu.facades;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.interfaces.ReservationInterface;

@Service
public class ReservationFacade implements ReservationInterface{

	@Autowired
	SqlSession sqlSession;

	@Override
	public void insertResv(ReservationModel rm) {
		this.sqlSession.insert("ReservationFacade.makeReservation",rm);
	}

	@Override
	public List<String> getResvId(String phoneNumber) {
		return sqlSession.selectList("ReservationFacade.getReservationId",phoneNumber);
	}

	@Override
	public void deleteResv(String phoneNumber,String reservationId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);
		map.put("reservationId", reservationId);
		this.sqlSession.delete("ReservationFacade.deleteReservation",map);
	}

	@Override
	public ReservationModel getResv(String phoneNumber,String reservationId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("phoneNumber", phoneNumber);
		map.put("reservationId", reservationId);
		return sqlSession.selectOne("ReservationFacade.getReservation", map);
	}

	@Override
	public void deleteResvByPhoneNumber(String phoneNumber) throws DataAccessException {
		this.sqlSession.delete("ReservationFacade.deleteReservationByPhoneNumber",phoneNumber);
	}

	@Override
	public void updateResv(ReservationModel rm) throws DataAccessException {
		this.sqlSession.update("ReservationFacade.updateReservation", rm);
	}
}
