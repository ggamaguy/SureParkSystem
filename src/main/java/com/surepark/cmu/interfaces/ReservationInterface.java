package com.surepark.cmu.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.ReservationModel;

public interface ReservationInterface {

	public void insertResv(ReservationModel rm) throws DataAccessException;
	public ReservationModel getResv(String reservationId) throws DataAccessException;
	public void deleteResv(String reservationId) throws DataAccessException;	
	public List<String> getResvId(String phoneNumber) throws DataAccessException;
	public void deleteResvByPhoneNumber(String phoneNumber) throws DataAccessException;
	public void updateResv(ReservationModel rm) throws DataAccessException;
}
