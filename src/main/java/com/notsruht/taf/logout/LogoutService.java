package com.notsruht.taf.logout;

import org.openqa.selenium.WebDriver;

/**
 * The interface for log out. The class that is interested in logging out from an application implements this interface and should be a Spring managed bean.
 *
 */
public interface LogoutService {
	/**
	 * Logs out of an application 
	 * 
	 * @param driver the webdriver
	 */
	public void logout(WebDriver driver);
}