package com.surepark.cmu.interfaces;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.ReservationModel;

public interface ReservationInterface {

	public void insertResv(ReservationModel rm) throws DataAccessException;
	public ReservationModel getResv(String phoneNumber,String reservationID) throws DataAccessException;
	public void deleteResv(String phoneNumber,String reservationId) throws DataAccessException;	
	public List<String> getResvId(String phoneNumber) throws DataAccessException;
	public void deleteResvByPhoneNumber(String phoneNumber) throws DataAccessException;
	public void updateResv(ReservationModel rm) throws DataAccessException;
	public void updateEntranceTime(ReservationModel rm) throws DataAccessException;
	public void updateExitTime(ReservationModel rm) throws DataAccessException;
}
