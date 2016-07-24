package com.surepark.cmu.facades;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.interfaces.DriverInterface;

@Service
public class DriverFacade implements DriverInterface{
	
	@Autowired
	@Qualifier("firstSqlSessionTemplate")
	SqlSession sqlSession;

	@Override
	public void registerDiver(DriverModel driver) throws DataAccessException {
		
		int result = sqlSession.insert("DriverFacade.registerDiver", driver);
		
	}

	@Override
	public DriverModel loginDriver(DriverModel driver) throws DataAccessException {
		DriverModel resDriver = null;
		resDriver = sqlSession.selectOne("DriverFacade.loginDriver", driver);
		if (resDriver == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", resDriver.getPhoneNumber()));
		}
		
		
		return resDriver;
	}

	@Override
	public DriverModel findDriver(String phoneNumber) throws DataAccessException {
		DriverModel driver = null;
		driver = sqlSession.selectOne("DriverFacade.findDriver", phoneNumber);
		if (driver == null) {
			throw new UsernameNotFoundException(String.format("Driver phoneNumber %s does not exist!", phoneNumber));
		}
		//System.out.println(user.toString());
		
		return driver;
	}

	@Override
	public void deleteDriver(String phoneNumber) throws DataAccessException {

		int result = sqlSession.delete("DriverFacade.deleteDriver", phoneNumber);

	}
	
	

}
