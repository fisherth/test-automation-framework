package com.notsruht.taf.ui;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class anywhere a screenshot is needed.
 * 
 *
 */
public class ScreenshotUtil {
	/**
	 * Takes a screenshot
	 * @param driver WebDriver
	 * @return Byte array of screenshot contents
	 */
	public static byte[] takeScreenshot(WebDriver driver) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		return screenshot;
	}
	
}
