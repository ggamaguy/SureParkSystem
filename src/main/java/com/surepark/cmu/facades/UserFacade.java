package com.surepark.cmu.facades;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.interfaces.UserInterface;

@Service
public class UserFacade implements UserInterface{

	@Autowired
	@Qualifier("firstSqlSessionTemplate")
	SqlSession sqlSession;
	
	@Override
	public void registerUserDiver(UserDriverModel userDriver) throws DataAccessException{
		// TODO Auto-generated method stub
		int result = sqlSession.insert("UserFacade.registerUserDiver", userDriver);
		
	}

	@Override
	public UserDriverModel loginUserDriver(UserDriverModel userDriver) throws DataAccessException, UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		UserDriverModel user = null;
		user = sqlSession.selectOne("UserFacade.loginUserDriver", userDriver);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", userDriver.getPhoneNumber()));
		}
		
		
		return user;
	}

	@Override
	public UserDriverModel findUserDriver(String phoneNumber) throws DataAccessException,UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDriverModel user = null;
		user = sqlSession.selectOne("UserFacade.findUserDriver", phoneNumber);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", phoneNumber));
		}
		//System.out.println(user.toString());
		
		return user;
	}


}
