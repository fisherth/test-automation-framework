package com.notsruht.taf.login;

import org.openqa.selenium.WebDriver;

/**
 * The interface for log in. The class that is interested in logging into an application implements this interface and should be a Spring managed bean.
 *
 */
public interface LoginService {
	/**
	 * Logs into an application using values from testautomationframework.properties for the encrypted username and password:
	 * 
	 * 
	 * @param driver the WebDriver
	 */
	public void login(WebDriver driver);
	
	/**
	 * Logs into an application
	 * 
	 * @param driver the WebDriver
	 * @param username the username
	 * @param password the password
	 */
	public void login(WebDriver driver, String username, String password);
}