/**
 * 
 */
package com.surepark.cmu.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author stunstun(minhyuck.jung@nhnent.com)
 *
 */
@EnableConfigurationProperties(value = MasterDatabaseProperties.class)
public abstract class DatabaseConfig {
	
	@Autowired
	private MasterDatabaseProperties masterDatabaseProperties;

    @Bean
    public abstract DataSource dataSource();

    protected void configureDataSource(org.apache.tomcat.jdbc.pool.DataSource dataSource) {
    	dataSource.setDriverClassName(masterDatabaseProperties.getDriverClassName());
    	dataSource.setUrl(masterDatabaseProperties.getUrl());
    	dataSource.setUsername(masterDatabaseProperties.getUserName());
    	dataSource.setPassword(masterDatabaseProperties.getPassword());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
    }
}

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.surepark.cmu.facades"}, sqlSessionFactoryRef = "firstSqlSessionFactory")
class DefaultDatabaseConfig extends DatabaseConfig {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
		configureDataSource(dataSource);
		return dataSource;
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource());
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
    }
	
	@Bean
	public SqlSessionFactory firstSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setTypeAliasesPackage("com.surepark.cmu.domains");
		sessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:META-INF/mybatis/mapper/**/*.xml"));
		return sessionFactoryBean.getObject();
	}
	
	 @Bean
	 public SqlSessionTemplate firstSqlSessionTemplate(SqlSessionFactory firstSqlSessionFactory) throws Exception {

	        return new SqlSessionTemplate(firstSqlSessionFactory);
	 }
}