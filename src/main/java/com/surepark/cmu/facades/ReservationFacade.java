package com.surepark.cmu.facades;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.ReservationModel;
import com.surepark.cmu.interfaces.ReservationInterface;

@Service
public class ReservationFacade implements ReservationInterface{

	//@Autowired
	//SqlSession sqlSession;

	@Override
	public void insertResv(ReservationModel rm) {
		//this.sqlSession.insert("",rm);
		
	}

	@Override
	public void updateResv(ReservationModel rm) {
		//this.sqlSession.update("", rm);
	}

	@Override
	public void deleteResv(String key) {
		//this.sqlSession.delete("");
	}
}
