package com.notsruht.taf.hooks;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.notsruht.taf.logout.LogoutService;
import com.notsruht.taf.ui.DriverType;
import com.notsruht.taf.ui.ScreenshotUtil;
import com.notsruht.taf.util.TAFProperties;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * <p>
 * Hooks are code that run before/after each scenario or each step in a scenario.
 * They are helpful in reducing code redundancy.
 * </p>
 * 
 * <p>
 * This class defines Hooks for UI scenarios in the Test Automation Framework. 
 * </p>
 * 
 * <p>
 * <b>NOTE: All UI Scenarios should have the "{@literal @}ui" tag specified on them.</b> 
 * </p>
 */
public class UIScenarioHooks {
	private static final String CAPTURE_SCREENSHOT = "testautomationframework.screenshot.eachstep";
	private static final String WEBDRIVER_TYPE_PROPERTY = "testautomationframework.webdriver.type";
	private static final String WEBDRIVER_BEAN = "tafWebDriver";
	
	@Autowired
	private ApplicationContext context;
	
	
	/**
	 * Sets the scenario name on the Web driver when running Cucumber tests on Proof
	 * 
	 * @param scenario the scenario
	 */
	@Before("@ui")
	public void beforeUiScenario(Scenario scenario) {
		String driverTypeString = TAFProperties.getInstance().getProperty(WEBDRIVER_TYPE_PROPERTY);
		if(driverTypeString != null && DriverType.REMOTE.equals(DriverType.valueOf(driverTypeString.trim().toUpperCase()))) {
			RemoteWebDriver remoteDriver = (RemoteWebDriver)context.getBean(WEBDRIVER_BEAN);
			MutableCapabilities capabilities = (MutableCapabilities)remoteDriver.getCapabilities();
			
			//Set the scenario name on the remote web driver
			capabilities.setCapability("testCaseName", scenario.getName());
		}		
	}
	
	/**
	 * Captures a screenshot if the scenario is failed and we don't want to capture one after each step.
	 * 
	 * @param scenario the scenario
	 */
	@After("@ui")
	public void afterUiScenario(Scenario scenario) {
		String captureScreenshotEachStepString = TAFProperties.getInstance().getProperty(CAPTURE_SCREENSHOT);
		WebDriver webDriver = (WebDriver)context.getBean(WEBDRIVER_BEAN);
		if(scenario.isFailed() && (captureScreenshotEachStepString == null || !Boolean.valueOf(captureScreenshotEachStepString))) {
			// Capture a screenshot if the scenario is failed and we don't want to capture after each step
			captureScreenshot(webDriver, scenario);
		}	
	}
	
	/**
	 * Captures a screenshot after each step if the testautomationframework.screenshot.eachstep property is true
	 * 
	 * @param scenario the scenario
	 */
	@AfterStep("@ui")
	public void afterUiStep(Scenario scenario) {
		String captureScreenshotEachStepString = TAFProperties.getInstance().getProperty(CAPTURE_SCREENSHOT);
		if(captureScreenshotEachStepString != null && Boolean.valueOf(captureScreenshotEachStepString)) {
			WebDriver webDriver = (WebDriver)context.getBean(WEBDRIVER_BEAN);
			captureScreenshot(webDriver, scenario);
		}
	}
	
	private void captureScreenshot(WebDriver webDriver, Scenario scenario) {
		byte[] screenshot = ScreenshotUtil.takeScreenshot(webDriver);
		scenario.attach(screenshot, "image/png", "screenshot_" + System.currentTimeMillis());
	}
}
