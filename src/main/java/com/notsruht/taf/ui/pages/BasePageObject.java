package com.notsruht.taf.ui.pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.notsruht.taf.ui.WebElementLocatorUtil;

/**
 * Base class for all page objects
 */
public abstract class BasePageObject implements PageObject {
	private WebDriver driver;
	private String pageURL;
	
	/**
	 * Creates a new instance of BasePageObject
	 * 
	 * @param driver the WebDriver instance used
	 */
	protected BasePageObject(WebDriver driver) {
		super();
		this.driver = driver;		
	}	
	
	@Override
	public WebElement findWebElementById(String id) {
		return WebElementLocatorUtil.findWebElementById(getDriver(), id);
	}
	
	@Override
	public WebElement findWebElement(By locatorType) {
		return WebElementLocatorUtil.findWebElement(getDriver(), locatorType);
	}

	@Override
	public List<WebElement> findWebElements(By locatorType) {
		return WebElementLocatorUtil.findWebElements(getDriver(), locatorType);
	}	

	@Override
	public WebElement findWebElementByName(String name) {
		return WebElementLocatorUtil.findWebElement(getDriver(), By.name(name));
	}

	@Override
	public WebElement findWebElementByClass(String className) {
		return WebElementLocatorUtil.findWebElementByClass(getDriver(), className);
	}

	@Override
	public WebElement findWebElementByLinkText(String linkText) {
		return WebElementLocatorUtil.findWebElementByLinkText(getDriver(), linkText);
	}

	@Override
	public WebElement findWebElementByPartialLinkText(String partialLinkText) {
		return WebElementLocatorUtil.findWebElementByPartialLinkText(getDriver(), partialLinkText);
	}

	@Override
	public WebElement findWebElementByTagname(String tagname) {
		return WebElementLocatorUtil.findWebElementByTagname(getDriver(), tagname);
	}

	@Override
	public WebElement findWebElementByCssSelector(String cssSelector) {
		return WebElementLocatorUtil.findWebElementByCssSelector(getDriver(), cssSelector);
	}

	@Override
	public WebElement findWebElementByXpath(String xpath) {
		return WebElementLocatorUtil.findWebElementByXpath(getDriver(), xpath);
	}

	@Override
	public List<WebElement> findWebElementsByName(String name) {
		return WebElementLocatorUtil.findWebElementsByName(getDriver(), name);
	}

	@Override
	public List<WebElement> findWebElementsByClass(String className) {
		return WebElementLocatorUtil.findWebElementsByClass(getDriver(), className);
	}

	@Override
	public List<WebElement> findWebElementsByLinkText(String linkText) {
		return WebElementLocatorUtil.findWebElementsByLinkText(getDriver(), linkText);
	}

	@Override
	public List<WebElement> findWebElementsByPartialLinkText(String partialLinkText) {
		return WebElementLocatorUtil.findWebElementsByPartialLinkText(getDriver(), partialLinkText);
	}

	@Override
	public List<WebElement> findWebElementsByTagname(String tagname) {
		return WebElementLocatorUtil.findWebElementsByTagname(getDriver(), tagname);
	}

	@Override
	public List<WebElement> findWebElementsByCssSelector(String cssSelector) {
		return WebElementLocatorUtil.findWebElementsByCssSelector(getDriver(), cssSelector);
	}

	@Override
	public List<WebElement> findWebElementsByXpath(String xpath) {
		return WebElementLocatorUtil.findWebElementsByXpath(getDriver(), xpath);
	}
	
	@Override
	public WebElement waitForElementToBeClickable(Long timeOutInSeconds, Long sleepInMillis, WebElement element) {
		return WebElementLocatorUtil.waitForElementToBeClickable(getDriver(), timeOutInSeconds, sleepInMillis, element);
	}

	@Override
	public WebElement waitForElementToBePresent(Long timeOutInSeconds, Long sleepInMillis, By locator) {
		return WebElementLocatorUtil.waitForElementToBePresent(getDriver(), timeOutInSeconds, sleepInMillis, locator);
	}

	@Override
	public WebElement waitForElementToBeVisible(Long timeOutInSeconds, Long sleepInMillis, WebElement element) {
		return WebElementLocatorUtil.waitForElementToBeVisible(getDriver(), timeOutInSeconds, sleepInMillis, element);
	}

	@Override
	public List<WebElement> waitForElementsToBeVisible(Long timeOutInSeconds, Long sleepInMillis,
			WebElement... elements) {
		return WebElementLocatorUtil.waitForElementsToBeVisible(getDriver(), timeOutInSeconds, sleepInMillis, elements);
	}

	@Override
	public List<WebElement> waitForElementsToBeVisible(Long timeOutInSeconds, Long sleepInMillis,
			List<WebElement> elements) {
		return WebElementLocatorUtil.waitForElementsToBeVisible(getDriver(), timeOutInSeconds, sleepInMillis, elements);
	}

	@Override
	public Boolean waitForOptionsToLoad(Select dropdownElement, Long timeOutInSeconds, Long sleepInMillis,
			Integer minNumberOfOptions) {
		return WebElementLocatorUtil.waitForOptionsToLoad(dropdownElement, timeOutInSeconds, sleepInMillis, minNumberOfOptions);
	}

	@Override
	public String getPageURL() {
		return pageURL;
	}

	@Override
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;		
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}
	
	@Override
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public void uploadFile(String fileId, String localFileDir, String fileName) {
		File file = new File(localFileDir,fileName);
		WebElement fileInput = findWebElementById(fileId);
		fileInput.sendKeys(file.getPath());
	}

	@Override
	public void clickButton(WebElement button) {
		new Actions(getDriver()).moveToElement(button).click().perform();
	}	
}