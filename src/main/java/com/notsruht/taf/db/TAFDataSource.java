package com.notsruht.taf.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import com.notsruht.taf.security.Credential;
import com.notsruht.taf.security.CredentialManagerFactory;
import com.notsruht.taf.util.TAFProperties;

/**
 * <p>
 * Database connection pool for the Test Automation Framework that extends the Tomcat JDBC Connection Pool.
 * 
 * <p>
 * Instances of this class are configured to be Spring Beans for eRA Databases in {@link com.notsruht.taf.config.TAFSpringConfiguration}.
 * </p>
 * 
 * @see <a href="https://tomcat.apache.org/tomcat-9.0-doc/jdbc-pool.html">The Tomcat JDBC Connection Pool</a>
 */
public class TAFDataSource extends DataSource {
	private static final String DATASOURCE_PROPERTY_PREFIX = "testautomationframework.datasource";	
	private static final String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
	private String databaseName;
	private String environment;
	
	public TAFDataSource(String databaseName) {
		this(databaseName, TAFProperties.getInstance().getProperty("testautomationframework.environment").toLowerCase());
	}
	
	public TAFDataSource(String databaseName, String environment) {
		this.databaseName = databaseName;
		this.environment = environment;
		setPoolProperties(compilePoolProperties());			
	}
	
	private PoolProperties compilePoolProperties() {
		TAFProperties props = TAFProperties.getInstance();		
		PoolProperties poolProps = new PoolProperties();
		
		setDatabaseUrl(getProperty(props,"url",environment), poolProps);
		setDriverClass(getProperty(props,"driverClass",environment), poolProps);
		setLoginCredentials(getProperty(props, "credentialManagerKey", environment), poolProps);
        setIsJmxEnabled(getProperty(props,"enableJmx",environment), poolProps);
		setIsTestWhileIdle(getProperty(props,"testWhileIdle",environment), poolProps);
		setIsTestOnBorrow(getProperty(props, "testOnBorrow", environment), poolProps);
		setValidationQuery(getProperty(props, "validationQuery", environment), poolProps);
		setIsTestOnReturn(getProperty(props, "testOnReturn", environment), poolProps);
		setValidationInterval(getProperty(props, "validationInterval", environment),poolProps);
		setTimeBetweenEvictionRuns(getProperty(props, "timeBetweenEvictionRuns", environment), poolProps);
		setMaxActive(getProperty(props, "maxActive", environment),poolProps);
		setInitialSize(getProperty(props, "initialSize", environment),poolProps);
		setMaxWait(getProperty(props, "maxWait", environment),poolProps);   
		setLogAbandoned(getProperty(props, "logAbandoned", environment),poolProps);
        setIsRemoveAbandoned(getProperty(props, "removeAbandoned", environment),poolProps);
        setRemoveAbandonedTimeout(getProperty(props, "removeAbandonedTimeout", environment),poolProps);
        setMinEvictableIdleTimeout(getProperty(props, "minEvictableIdleTime", environment),poolProps);
        setMinIdle(getProperty(props, "minIdle", environment),poolProps);
        setMaxIdle(getProperty(props, "maxIdle", environment),poolProps);
        setJdbcInterceptors(getProperty(props, "jdbcInterceptors", environment),poolProps);        
        
        return poolProps;
	}
	
	private void setLoginCredentials(String credentialKey, PoolProperties poolProps) {
		if(credentialKey != null) {
			Credential credential = CredentialManagerFactory.getCredentialManager().getCredential(credentialKey);
			poolProps.setUsername(credential.getUsername());
			poolProps.setPassword(credential.getPassword());			
		}
	}
	
	private String getProperty(TAFProperties properties, String poolProperty, String environment) {
		String propertyName = DATASOURCE_PROPERTY_PREFIX + "." + getDatabaseName() + "." + poolProperty + "." + environment;
		return properties.getProperty(propertyName);
	}	
	
	private void setDatabaseUrl(String dbUrl, PoolProperties poolProps) {
		if(dbUrl != null) {
			poolProps.setUrl(dbUrl);
		}
	}
	
	private void setDriverClass(String driverClass, PoolProperties poolProps) {
		if(driverClass != null) {
			poolProps.setDriverClassName(driverClass);
		} else {
			poolProps.setDriverClassName(ORACLE_DRIVER_CLASS_NAME);
		}
	}
	
	private void setIsJmxEnabled(String isJmxEnabled, PoolProperties poolProps) {
		if(isJmxEnabled != null) {
        	poolProps.setJmxEnabled(Boolean.valueOf(isJmxEnabled));
		} else {
			poolProps.setJmxEnabled(Boolean.FALSE);
		}
	}
	
	private void setIsTestWhileIdle(String isTestWhileIdle, PoolProperties poolProps) {
		if(isTestWhileIdle != null) {
        	poolProps.setTestWhileIdle(Boolean.valueOf(isTestWhileIdle));
		} else {
			poolProps.setTestWhileIdle(Boolean.FALSE);
		}
	}
	
	private void setIsTestOnBorrow(String isTestOnBorrow, PoolProperties poolProps) {
		if(isTestOnBorrow != null) {
        	poolProps.setTestOnBorrow(Boolean.valueOf(isTestOnBorrow));
		} else {
			poolProps.setTestOnBorrow(Boolean.FALSE);
		}
	}
	
	private void setValidationQuery(String validationQuery, PoolProperties poolProps) {
		if(validationQuery != null) {
			poolProps.setValidationQuery(validationQuery);
		}
	}
	
	private void setIsTestOnReturn(String isTestOnReturn, PoolProperties poolProps) {
		if(isTestOnReturn != null) {
        	poolProps.setTestOnReturn(Boolean.valueOf(isTestOnReturn));
		} else {
			poolProps.setTestOnReturn(Boolean.FALSE);
		}
	}
	
	private void setValidationInterval(String validationInterval, PoolProperties poolProps) {
		if(validationInterval != null) {
			poolProps.setValidationInterval(Long.valueOf(validationInterval));
		}
	}
	
	private void setTimeBetweenEvictionRuns(String timeBetweenEvictionRuns, PoolProperties poolProps) {
		if(timeBetweenEvictionRuns != null) {
			poolProps.setTimeBetweenEvictionRunsMillis(Integer.valueOf(timeBetweenEvictionRuns));
		}
	}
	
	private void setMaxActive(String maxActive, PoolProperties poolProps) {
		if(maxActive != null) {
			poolProps.setMaxActive(Integer.valueOf(maxActive));
		}
	}
	
	private void setInitialSize(String initialSize, PoolProperties poolProps) {
		if(initialSize != null) {
			poolProps.setInitialSize(Integer.valueOf(initialSize));
		} else {
			poolProps.setInitialSize(1);
		}
	}
	
	private void setMaxWait(String maxWait, PoolProperties poolProps) {
		if(maxWait != null) {
			poolProps.setMaxWait(Integer.valueOf(maxWait));
		}
	}
	
	private void setLogAbandoned(String isLogAbandoned, PoolProperties poolProps) {
		if(isLogAbandoned != null) {
        	poolProps.setLogAbandoned(Boolean.valueOf(isLogAbandoned));
		}
	}
	
	private void setIsRemoveAbandoned(String isRemoveAbandoned, PoolProperties poolProps) {
		if(isRemoveAbandoned != null) {
        	poolProps.setRemoveAbandoned(Boolean.valueOf(isRemoveAbandoned));
		} else {
			poolProps.setRemoveAbandoned(Boolean.TRUE);
		}
	}
	
	private void setRemoveAbandonedTimeout(String removeAbandonedTimeout, PoolProperties poolProps) {
		if(removeAbandonedTimeout != null) {
			poolProps.setRemoveAbandonedTimeout(Integer.valueOf(removeAbandonedTimeout));
		}
	}
	
	private void setMinEvictableIdleTimeout(String minEvictableIdleTime, PoolProperties poolProps) {
		//default is 60 seconds
        if(minEvictableIdleTime != null) {
			poolProps.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTime));
		}
	}
	
	private void setMinIdle(String minIdle, PoolProperties poolProps) {
		//default is 1
        if(minIdle != null) {
			poolProps.setMinIdle(Integer.valueOf(minIdle));
		} else {
			poolProps.setMinIdle(1);
		}
	}
	
	private void setMaxIdle(String maxIdle, PoolProperties poolProps) {
		//default is 5
        if(maxIdle != null) {
			poolProps.setMaxIdle(Integer.valueOf(maxIdle));
		} else {
			poolProps.setMaxIdle(5);
		}        
	}
	
	private void setJdbcInterceptors(String jdbcInterceptors, PoolProperties poolProps) {
		if(jdbcInterceptors != null) {
			poolProps.setJdbcInterceptors(jdbcInterceptors);
		}  
	}
	
	/**
	 * The name of the database to connect to, for example "oltp", "irdb", etc.
	 * 
	 * @return the database name
	 */
	public String getDatabaseName() {
		return this.databaseName;
	}	
}