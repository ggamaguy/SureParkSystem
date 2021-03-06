package com.surepark.cmu.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.surepark.cmu.services.CustomDriverDetailsService;
import com.surepark.cmu.services.CustomOwnerDetatilsService;

@Configuration
public class OAuth2ServerConfiguration {

	private static final String RESOURCE_ID = "restservice";

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// @formatter:off
			resources.resourceId(RESOURCE_ID);
			// @formatter:on
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off

			//System.out.println("??");
			http
				.authorizeRequests()
					.antMatchers("/users/{userPhoneNumber}").authenticated()
					.antMatchers("/drivers/{phoneNumber}").authenticated()
					.antMatchers("/drivers/handover/{phoneNumber}").authenticated()
					.antMatchers("/cardvalidate").authenticated()
					.antMatchers("/opengate/{phoneNumber}/{resrvationID}").authenticated()
					.antMatchers("/reservations").authenticated()
					.antMatchers("/reservations/{reservationId}").authenticated()
					.antMatchers("/noshow/{phoneNumber}").authenticated()
					.antMatchers("/sureparks/list/{cityName}").authenticated()
					.antMatchers("/sureparks/sync/{parkingLotId}").authenticated()
					.antMatchers("/payment/{phoneNumber}/{reservationId}").authenticated();
			// @formatter:on
		}

	}

	@Configuration
	@EnableAuthorizationServer
	public static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		public static TokenStore tokenStore = new InMemoryTokenStore();

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		
		@Autowired
		private CustomDriverDetailsService driverDetailsService;
		
		@Autowired
		private CustomOwnerDetatilsService ownerDetailsService;

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			//System.out.println("??");
			
			oauthServer.allowFormAuthenticationForClients();
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			//System.out.println("??");
			// @formatter:off
			
			endpoints.tokenStore(this.tokenStore).authenticationManager(this.authenticationManager)
					.userDetailsService(driverDetailsService)
					.userDetailsService(ownerDetailsService);
			// @formatter:on
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			//System.out.println("??");
			// @formatter:off

			clients.inMemory()
						.withClient("user_driver")
								.authorizedGrantTypes("authorization_code","password", "refresh_token")
								.authorities("USER")
								.scopes("read", "write")
								.resourceIds(RESOURCE_ID).secret("123456")
								.accessTokenValiditySeconds(60 * 60 * 3)
						.and()
						.withClient("owner")
								.authorizedGrantTypes("authorization_code","password", "refresh_token")
								.authorities("ADMIN")
								.scopes("read", "write")
								.resourceIds(RESOURCE_ID).secret("123456")
								.accessTokenValiditySeconds(60 * 60 * 3);

			// @formatter:on
		}

		@Bean
		@Primary
		public DefaultTokenServices tokenServices() {
			DefaultTokenServices tokenServices = new DefaultTokenServices();
			tokenServices.setSupportRefreshToken(true);
			tokenServices.setTokenStore(this.tokenStore);
			return tokenServices;
		}

	}
}
