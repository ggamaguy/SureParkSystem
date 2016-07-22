package com.surepark.cmu.facades;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.interfaces.UserInterface;

public class UserFacade implements UserInterface{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void registerUserDiver(UserDriverModel userDriver) throws DataAccessException{
		// TODO Auto-generated method stub
		int result = sqlSession.insert("UserFacade.registerUserDiver", userDriver);
		
	}

}
