package com.notsruht.taf.util;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Singleton representing the configurable properties used in the Test Automation Framework. 
 * 
 * Properties are specified by:
 * 
 * <ol>
 * 	<li>A comma-separated string of filenames on the classpath named by the value of the "testautomationframework.propertiesFiles" system property<br>
 * 		<b>or</b><br>A file on the classpath named testautomationframework.properties</li>
 *  <li>System properties</li>
 * </ol>
 * 
 * <b>NOTE: A system property value will take precedence over a property value defined in a file.</b>
 */
public class TAFProperties {
	private static final long serialVersionUID = 1L;
	private static final String TAF_PROPERTIES_FILE_PROPERTY = "testautomationframework.propertiesFiles";
	private static final String DEFAULT_TAF_PROPERTIES_FILE = "testautomationframework.properties";
	private static final Logger LOG = LogManager.getLogger(TAFProperties.class);
	private Properties properties = null;    
	
    private TAFProperties() {
    	properties = new Properties();
        String userSpecifiedPropertiesFile = System.getProperty(TAF_PROPERTIES_FILE_PROPERTY);
        String propertiesFilesStr = userSpecifiedPropertiesFile != null ? userSpecifiedPropertiesFile : DEFAULT_TAF_PROPERTIES_FILE;
        String[] propertiesFiles = propertiesFilesStr.split(",");
        
        for(int i = 0; i < propertiesFiles.length; i++) {
        	String propertiesFile = propertiesFiles[i];
        	LOG.info("Loading properties file " + propertiesFile);
        	
        	try {
        		properties.load(TAFProperties.class.getClassLoader().getResourceAsStream(propertiesFile));
        	} catch (Exception e) {
        		if(LOG.isDebugEnabled()) {
        			LOG.debug("Could not load " + propertiesFile, e);
        		} else {
        			LOG.warn("Could not load " + propertiesFile + ". Make sure it's in your classpath if you want to use it.");
        		}            
        	}
        }
        
        // Replace entries from the properties file with ones from the System Properties
        properties.putAll(System.getProperties());        
    }
    
    /**
	 * Gets the value of a property.
	 * 
	 * @param propertyName The name of the property
	 * @return The value of the property from the System properties or testautomationframework.properties 
	 * if not found in the System properties.
	 */
    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
    
    /**
     * Sets a Test Automation Framework property
     * @param key property name
     * @param value property value
     * @return the previous value of the specified key in this propertylist, or null if it did not have one.
     */
    public String setProperty(String key, String value) {
    	return (String)properties.setProperty(key, value);
    }
    
    /**
     * Provides access to the framework properties
     * 
     * @return All framework properties
     */
    public Properties getProperties() {
    	return properties;
    }
	
	private static class Singleton {
        static final TAFProperties INSTANCE = new TAFProperties();
    }

	/**
	 * Provides access to the TAFProperties singleton
	 * 
	 * @return The one TAFProperties instance
	 */
    public static TAFProperties getInstance() {
        return Singleton.INSTANCE;
    }
}