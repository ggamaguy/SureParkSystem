/**
 * 
 */
package com.surepark.cmu.mybatis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author stunstun(minhyuck.jung@nhnent.com)
 *
 */
@ConfigurationProperties(prefix = MasterDatabaseProperties.PREFIX)
public class MasterDatabaseProperties implements DatabaseProperties {
	
	public static final String PREFIX = "datasource.master"; 

	private String driverClassName;
	
	private String url;
	
	private String userName;
	
	private String password;
	

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}