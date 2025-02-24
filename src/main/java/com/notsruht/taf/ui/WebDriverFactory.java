package com.notsruht.taf.ui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.notsruht.taf.util.TAFProperties;

/**
 * Factory class used to provide WebDriver instances
 */
public class WebDriverFactory {
	private static final String BROWSER_PROPERTY = "testautomationframework.browser";
	private static final String PROJECT_NAME_PROPERTY = "testautomationframework.application.name";
	private static final String PROJECT_BUILD_PROPERTY = "testautomationframework.application.build";
	private static final String WEBDRIVER_TYPE_PROPERTY = "testautomationframework.webdriver.type";
	private static final String ARGUMENT_PROPERTY_PREFIX = "testautomationframework.browser.argument.";
	private static final String BROWSER_BINARY_PROPERTY_PREFIX = "testautomationframework.browser.path.";
	private static final String WEBDRIVER_PROPERTY_PREFIX = "testautomationframework.webdriver.path.";
	private static final String REMOTE_URL_PROPERTY = "testautomationframework.webdriver.remote.url";
	private static final String REMOTE_URL_CREDENTIAL_MANAGER_KEY_PROPERTY = "testautomationframework.webdriver.remote.credentialManagerKey";
	private static final String CHROME_DRIVER_SYSTEM_PROPERTY = "webdriver.chrome.driver";	
	private static final String EDGE_DRIVER_SYSTEM_PROPERTY = "webdriver.edge.driver";
	private static final String FIREFOX_DRIVER_SYSTEM_PROPERTY = "webdriver.gecko.driver";
	private static final Logger LOG = LogManager.getLogger(WebDriverFactory.class);
	
	
	public WebDriverFactory() {	
	
	}
	
	/**
	 * Creates an instance of WebDriver as defined in system properties or testautomationframework.properties.
	 * 
	 * @return The WebDriver
	 */
	public static WebDriver getWebDriver() {
		DriverType driverType = getDriverType();
		BrowserVendor browserVendor = getBrowserVendor();
				
		return getWebDriver(driverType, browserVendor);		
	}
	
	/**
	 * Creates an instance of WebDriver
	 * 
	 * @param driverType The driver type
	 * @param browserVendor The browser vendor
	 * @return The WebDriver
	 */
	public static WebDriver getWebDriver(DriverType driverType, BrowserVendor browserVendor) {
		switch(driverType) {
		case LOCAL:
			return getLocalWebDriver(browserVendor);
		case REMOTE:
			return getRemoteWebDriver(browserVendor);			
		default:
			return getLocalWebDriver(browserVendor);
		}		
	}
	
	/**
	 * Retrieves the browser vendor from properties
	 * 
	 * @return The browser vendor specified in system properties or testautomationframework.properties or CHROME if not defined
	 */
	public static BrowserVendor getBrowserVendor() {
		String browserVendorStr = TAFProperties.getInstance().getProperty(BROWSER_PROPERTY);
		if(browserVendorStr != null && !browserVendorStr.trim().isEmpty()) {
			return BrowserVendor.valueOf(browserVendorStr.trim().toUpperCase());
		} 

		return BrowserVendor.CHROME;		
	}
	
	/**
	 * Retrieves the driver type from properties
	 * 
	 * @return The driver type specified in system properties or testautomationframework.properties or LOCAL if not defined
	 */
	public static DriverType getDriverType() {
		String driverTypeStr = TAFProperties.getInstance().getProperty(WEBDRIVER_TYPE_PROPERTY);
		if(driverTypeStr != null && !driverTypeStr.trim().isEmpty()) {
			return DriverType.valueOf(driverTypeStr.trim().toUpperCase());
		} 

		return DriverType.LOCAL;		
	}
	
	private static WebDriver getLocalWebDriver(BrowserVendor browserVendor) {
		switch(browserVendor) {
		case CHROME:
			return getLocalChromeDriver();
		case FIREFOX:
			return getLocalFirefoxDriver();
		case EDGE:
			return getLocalEdgeDriver();
		case SAFARI:
			return new SafariDriver();
		case HTMLUNIT:
			return getHtmlUnitDriver();
		default:
			return getLocalChromeDriver();
		}
	}	
	
	private static WebDriver getHtmlUnitDriver() {
		return new TAFHtmlUnitDriver(true);
	}

	private static WebDriver getLocalChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		String webDriverPath = getWebDriverPath(BrowserVendor.CHROME);
		if(webDriverPath != null && !webDriverPath.trim().isEmpty()) {
			LOG.info("Using user specified Chrome driver at " + webDriverPath);
			System.setProperty(CHROME_DRIVER_SYSTEM_PROPERTY, webDriverPath.trim());
			
			File browserBinaryFile = getBinaryFile(BrowserVendor.CHROME);
			if(browserBinaryFile != null) {
				LOG.info("Using user specified Chrome browser at " + browserBinaryFile.getPath());
				options.setBinary(browserBinaryFile);
			}
		} else {
			System.clearProperty(CHROME_DRIVER_SYSTEM_PROPERTY);
		}
					
		options.addArguments(getArguments(BrowserVendor.CHROME));		
		
		return new ChromeDriver(options);
	}
	
	private static WebDriver getLocalFirefoxDriver() {
		FirefoxOptions options = new FirefoxOptions();
		String webDriverPath = getWebDriverPath(BrowserVendor.FIREFOX);
		if(webDriverPath != null && !webDriverPath.trim().isEmpty()) {
			LOG.info("Using user specified Firefox driver at " + webDriverPath);
			System.setProperty(FIREFOX_DRIVER_SYSTEM_PROPERTY, webDriverPath.trim());
			
			File browserBinaryFile = getBinaryFile(BrowserVendor.FIREFOX);
			if(browserBinaryFile != null) {
				LOG.info("Using user specified Firefox browser at " + webDriverPath);
				options.setBinary(browserBinaryFile.getPath());
			}
		} else {
			System.clearProperty(FIREFOX_DRIVER_SYSTEM_PROPERTY);
		}
					
		options.addArguments(getArguments(BrowserVendor.FIREFOX));
		
		return new FirefoxDriver(options);
	}
	
	private static WebDriver getLocalEdgeDriver() {
		EdgeOptions options = new EdgeOptions();
		String webDriverPath = getWebDriverPath(BrowserVendor.EDGE);
		if(webDriverPath != null && !webDriverPath.trim().isEmpty()) {
			LOG.info("Using user specified Edge driver at " + webDriverPath);
			System.setProperty(EDGE_DRIVER_SYSTEM_PROPERTY, webDriverPath.trim());			
		} else {
			System.clearProperty(EDGE_DRIVER_SYSTEM_PROPERTY);
		}
		
		return new EdgeDriver(options);
	}
	
	private static String getWebDriverPath(BrowserVendor browserVendor) {
		String propertyName = WEBDRIVER_PROPERTY_PREFIX + browserVendor.toString().toLowerCase();
		String webDriverLocation = TAFProperties.getInstance().getProperty(propertyName);		
		
		LOG.debug("Webdriver Location = " + webDriverLocation);
		return webDriverLocation;
	}
	
	private static File getBinaryFile(BrowserVendor browserVendor) {
		String binaryPropertyName = BROWSER_BINARY_PROPERTY_PREFIX + browserVendor.toString().toLowerCase();
		String binaryPathStr = null;
		File binaryFile = null;
		if((binaryPathStr=TAFProperties.getInstance().getProperty(binaryPropertyName)) != null) {
			binaryFile = new File(binaryPathStr); 
		}
		
		return binaryFile;
	}
	
	private static List<String> getArguments(BrowserVendor browserVendor) {
		List<String> argsList = new ArrayList<>();
		String vendorStr = browserVendor.toString().toLowerCase();
		Set<Entry<Object,Object>> entries = TAFProperties.getInstance().getProperties().entrySet();		
		for(Entry<Object,Object> entry : entries) {
			String propertyName = entry.getKey().toString();	
			String browserProperty = getBrowserProperty(propertyName,vendorStr);
			
			if(propertyName.startsWith(ARGUMENT_PROPERTY_PREFIX) && propertyName.endsWith(vendorStr)) {
				String propertyValue = null;
				if(entry.getValue() != null && (!"".equals(propertyValue=entry.getValue().toString().trim()))) {
					argsList.add(browserProperty + "=" + propertyValue);
				} else {
					argsList.add(browserProperty);
				}
			}
		}		
		
		return argsList;
	}
	
	private static String getBrowserProperty(String propertyName, String vendorStr) {
		String browserProperty = propertyName.replace(ARGUMENT_PROPERTY_PREFIX, "");
		browserProperty = browserProperty.replace("." + vendorStr, "");
		
		return browserProperty;
	}

	private static WebDriver getRemoteWebDriver(BrowserVendor browserVendor) {
		MutableCapabilities capabilities = null;
		
		switch(browserVendor) {
		case CHROME:
			capabilities = new ChromeOptions();
			((ChromeOptions)capabilities).addArguments(getArguments(BrowserVendor.CHROME));	
			break;
		case FIREFOX:
			capabilities = new FirefoxOptions();
			((FirefoxOptions)capabilities).addArguments(getArguments(BrowserVendor.FIREFOX));	
			break;
		case EDGE:
			capabilities = new EdgeOptions();
			break;
		default:
			throw new IllegalArgumentException("RemoteWebDriver has not been implemented for " + browserVendor);
		}
		
		capabilities.setCapability("build", TAFProperties.getInstance().getProperty(PROJECT_BUILD_PROPERTY)); 
        capabilities.setCapability("projectName", TAFProperties.getInstance().getProperty(PROJECT_NAME_PROPERTY));
		
		try {
			return new RemoteWebDriver(getRemoteURL(), capabilities);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static URL getRemoteURL() throws MalformedURLException {
		throw new RuntimeException("Remote Test Execution has not been implemented.");
	}
}