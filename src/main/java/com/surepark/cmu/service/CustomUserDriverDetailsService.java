package com.surepark.cmu.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.surepark.cmu.domains.UserDriverModel;
import com.surepark.cmu.facades.UserFacade;




public class CustomUserDriverDetailsService implements UserDetailsService{

	@Autowired
	private UserFacade userFacade;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDriverModel userDriver = userFacade.loginUserDriver(userDriver);
		if (userDriver == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return new UserFacadeUserDetails(userDriver);
	}
	
	private final static class UserFacadeUserDetails extends UserDriverModel implements UserDetails {

		private static final long serialVersionUID = 1L;

		private UserFacadeUserDetails(UserDriverModel userDriver) {
			super(userDriver);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return getPhoneNumber();
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}

		

	}

}
