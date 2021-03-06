package com.surepark.cmu.facades;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.domains.HandoverDriverModel;
import com.surepark.cmu.domains.OwnerModel;
import com.surepark.cmu.interfaces.OwnerInterface;

@Service
public class OwnerFacade implements OwnerInterface {

	@Autowired
	@Qualifier("firstSqlSessionTemplate")
	SqlSession sqlSession;

	@Override
	public OwnerModel loginOwner(String ownerID, String ownerPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ownerID", ownerID);
		map.put("ownerPassword", ownerPassword);
		OwnerModel owner = sqlSession.selectOne("OwnerFacade.loginOwner", map);
		return owner;
	}

	@Override
	public OwnerModel findOwner(String ownerID) throws DataAccessException {
		// TODO Auto-generated method stub
		OwnerModel owner = null;
		owner = sqlSession.selectOne("OwnerFacade.findOwner", ownerID);
		if (owner == null) {
			throw new UsernameNotFoundException(String.format("Owner ID %s does not exist!", ownerID));
		}
		return owner;
	}

	@Override
	public void updateOwnerSecondPassword(String ownerID, String ownerSecondPwd) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ownerID", ownerID);
		map.put("ownerSecondPassword", ownerSecondPwd);

		int result = sqlSession.update("OwnerFacade.updateOwnerSecondPwd", map);
	}

	@Override
	public OwnerModel loginOwnerSecondPassword (String ownerID, String ownerSecondPwd) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ownerID", ownerID);
		map.put("ownerSecondPwd", ownerSecondPwd);
		OwnerModel owner = sqlSession.selectOne("OwnerFacade.loginOwnerTwoFactor", map);
		return owner;
	}

	@Override
	public OwnerModel getOwnerAvailable(String ownerID) throws DataAccessException {	
		return sqlSession.selectOne("OwnerFacade.getOwnerAccountAvailable",ownerID);
	}

	@Override
	public void updateOwnerAvailable(String ownerID, String owner1stLoginTry, String owner2ndLoginTry, String ownerAccountAvailable) throws DataAccessException {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("ownerID", ownerID);
		map.put("owner1stLoginTry", owner1stLoginTry);
		map.put("owner2ndLoginTry", owner2ndLoginTry);
		map.put("ownerAccountAvailable", ownerAccountAvailable);
		
		sqlSession.update("OwnerFacade.updateOwnerAvailable",map);
	}

	
}
