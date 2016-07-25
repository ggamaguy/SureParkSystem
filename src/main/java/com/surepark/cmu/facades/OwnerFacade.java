package com.surepark.cmu.facades;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.OwnerModel;
import com.surepark.cmu.interfaces.OwnerInterface;

@Service
public class OwnerFacade implements OwnerInterface{

	@Override
	public OwnerModel loginOwner(String ownerID, String ownerPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
        map.put("ownerID", ownerID);
        map.put("ownerPassword", ownerPassword);
        
        
        
		return null;
	}

	@Override
	public OwnerModel findOwner(String ownerID) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
