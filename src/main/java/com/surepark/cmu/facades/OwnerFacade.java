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
		// System.out.println(user.toString());

		return owner;
	}

	@Override
	public void updateOwnerTwofactor(String ownerID, String ownerTwofactorPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ownerID", ownerID);
		map.put("ownerTwofactorPassword", ownerTwofactorPassword);

		int result = sqlSession.update("OwnerFacade.updateOwnerTwofactor", map);

	}

	@Override
	public OwnerModel loginOwnerTwoFactor(String ownerID, String ownerTwofactorPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("ownerID", ownerID);
		map.put("ownerTwofactorPassword", ownerTwofactorPassword);

		OwnerModel owner = sqlSession.selectOne("OwnerFacade.loginOwnerTwoFactor", map);

		return owner;
	}

}
