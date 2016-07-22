package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.UserDriverModel;

public interface UserInterface {
	
	void registerUserDiver(UserDriverModel userDriver) throws DataAccessException;

}
