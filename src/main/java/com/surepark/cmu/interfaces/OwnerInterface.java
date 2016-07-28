package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.OwnerModel;

public interface OwnerInterface {
	
	OwnerModel loginOwner(String ownerID, String ownerPassword) throws DataAccessException;
	OwnerModel loginOwnerSecondPassword(String ownerID, String ownerSecondPwd) throws DataAccessException;
	OwnerModel findOwner(String ownerID) throws DataAccessException;
	OwnerModel getOwnerAvailable(String ownerID) throws DataAccessException;
	void updateOwnerAvailable(String ownerID, String owner1stLoginTry, String owner2ndLoginTry, String ownerAccountAvailable) throws DataAccessException;
	void updateOwnerSecondPassword(String ownerID, String ownerSecondPwd) throws DataAccessException;
	
	

}
