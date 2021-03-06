package com.surepark.cmu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.surepark.cmu.services.CustomDriverDetailsService;
import com.surepark.cmu.services.CustomOwnerDetatilsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomDriverDetailsService driverDetailsService;
	
	@Autowired
	private CustomOwnerDetatilsService ownerDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//System.out.println("??");
		 auth.userDetailsService(driverDetailsService);
		 auth.userDetailsService(ownerDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("??");
		
	       http
	          .csrf().disable();
	       http.anonymous();
	       
	 }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
