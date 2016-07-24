package com.surepark.cmu.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.surepark.cmu.domains.DriverModel;
import com.surepark.cmu.facades.DriverFacade;

@Service
public class CustomDriverDetailsService implements UserDetailsService{

	
	@Autowired
	private DriverFacade driverFacade;
	
	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		DriverModel driver = driverFacade.findDriver(phoneNumber);
		if (driver == null) {
			throw new UsernameNotFoundException(String.format("Driver phoneNumber %s does not exist!", phoneNumber));
		}
		//System.out.println(userDriver.toString());
		return new DriverFacadeUserDetails(driver);
	}
	
	private final static class DriverFacadeUserDetails extends DriverModel implements UserDetails {

		private static final long serialVersionUID = 1L;

		private DriverFacadeUserDetails(DriverModel driver) {
			super(driver);
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
			return getIdentificationNumber();
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return getPhoneNumber();
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
