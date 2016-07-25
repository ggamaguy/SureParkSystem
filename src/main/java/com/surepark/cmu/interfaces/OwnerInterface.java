package com.surepark.cmu.interfaces;

import org.springframework.dao.DataAccessException;

import com.surepark.cmu.domains.OwnerModel;

public interface OwnerInterface {
	
	OwnerModel loginOwner(String ownerID, String ownerPassword) throws DataAccessException;
	OwnerModel findOwner(String ownerID) throws DataAccessException;
	

}
