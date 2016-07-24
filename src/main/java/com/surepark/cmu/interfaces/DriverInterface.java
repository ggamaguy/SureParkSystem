package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;
import com.surepark.cmu.domains.DriverModel;

public interface DriverInterface {
	
	void registerDiver(DriverModel driver) throws DataAccessException;
	void deleteDriver(String phoneNumber) throws DataAccessException;
	DriverModel loginDriver(DriverModel driver) throws DataAccessException;
	DriverModel findDriver(String phoneNumber) throws DataAccessException;

}
