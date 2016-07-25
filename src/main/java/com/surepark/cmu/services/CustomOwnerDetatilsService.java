package com.surepark.cmu.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.surepark.cmu.domains.OwnerModel;
import com.surepark.cmu.facades.OwnerFacade;





@Service
public class CustomOwnerDetatilsService implements UserDetailsService{

	@Autowired
	private OwnerFacade ownerFacade;
	
	@Override
	public UserDetails loadUserByUsername(String ownerID) throws UsernameNotFoundException {
		OwnerModel owner = ownerFacade.findOwner(ownerID);
		if (owner == null) {
			throw new UsernameNotFoundException(String.format("Owner ID %s does not exist!", ownerID));
		}
		//System.out.println(userDriver.toString());
		return new OwnerFacadeUserDetails(owner);
	}
	
	private final static class OwnerFacadeUserDetails extends OwnerModel implements UserDetails {

		private static final long serialVersionUID = 1L;

		private OwnerFacadeUserDetails(OwnerModel owner) {
			super(owner);
			//System.out.println(userDriver.toString());
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub

			return null;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return getOwnerPassword();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return getOwnerID();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}

		

	}
}
