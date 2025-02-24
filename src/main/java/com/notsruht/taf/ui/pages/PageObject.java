package com.notsruht.taf.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public interface PageObject {
	

	/**
	 * Find web element by ID
	 * @param id the ID of the web element
	 * @return the web element
	 */
	public WebElement findWebElementById(String id);
	
	/**
	 * Find web element by the specified locator
	 * 
	 * @param locatorType the locator used to find the web elements
	 * @return the web element
	 */
	public WebElement findWebElement(By locatorType);
	
	/**
	 * Find web element by the name attribute. If multiple elements have the same name, then this method
	 * will always choose the first one.
	 * 
	 * @param name The value of the element's name attribute
	 * @return the web element
	 */
	public WebElement findWebElementByName(String name);
	
	/**
	 * Find web element by the class attribute. If multiple elements have the same class, then this method
	 * will always choose the first one.
	 * 
	 * @param className The value of the element's class attribute
	 * @return the web element
	 */
	public WebElement findWebElementByClass(String className);
	
	/**
	 * Find web element by hyperlink text. If multiple hyperlinks with the same texts are present on the page, then this method
	 * will always choose the first one.
	 * 
	 * @param linkText The hyperlink text
	 * @return the web element
	 */
	public WebElement findWebElementByLinkText(String linkText);
	
	/**
	 * Find web element by partial hyperlink text. If multiple hyperlinks with the same partial texts are present on the page, then this method
	 * will always choose the first one.
	 * 
	 * @param partialLinkText The partial hyperlink text
	 * @return the web element
	 */
	public WebElement findWebElementByPartialLinkText(String partialLinkText);
	
	/**
	 * Find web element by tag name. If multiple tags with the same name are present on the page, then this method
	 * will always choose the first one. 
	 * 
	 * @param tagname The tag name
	 * @return the web element
	 */
	public WebElement findWebElementByTagname(String tagname);
	
	/**
	 * Find web element by CSS selector. If the CSS selector matches more than one element, then this method will always choose the first one.
	 * 
	 * @param cssSelector The CSS selector
	 * @return the web element
	 */
	public WebElement findWebElementByCssSelector(String cssSelector);
	
	/**
	 * Find web element by Xpath expression. If the XPath expression matches more than one element, then this method will always choose the first one.
	 * @param xpath
	 * @return the web element
	 */
	public WebElement findWebElementByXpath(String xpath);
	
	/**
	 * Find web elements by the specified locator
	 * @param locatorType the locator used to find the web elements
	 * @return the web elements
	 */
	public List<WebElement> findWebElements(By locatorType);
	
	/**
	 * Find web elements by the name attribute.
	 * 
	 * @param name The value of the element's name attribute
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByName(String name);
	
	/**
	 * Find web elements by the class attribute.
	 * 
	 * @param className The value of the element's class attribute
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByClass(String className);
	
	/**
	 * Find web elements by hyperlink text.
	 * 
	 * @param linkText The hyperlink text
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByLinkText(String linkText);
	
	/**
	 * Find web elements by partial hyperlink text.
	 * 
	 * @param partialLinkText The partial hyperlink text
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByPartialLinkText(String partialLinkText);
	
	/**
	 * Find web elements by tag name.
	 * 
	 * @param tagname The tag name
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByTagname(String tagname);
	
	/**
	 * Find web elements by CSS selector.
	 * 
	 * @param cssSelector The CSS selector
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByCssSelector(String cssSelector);
	
	/**
	 * Find web elements by Xpath expression.
	 * @param xpath The XPath expression
	 * @return the web elements
	 */
	public List<WebElement> findWebElementsByXpath(String xpath);
	
	/**
	 * Wait for a WebElement to become visible and clickable
	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param element the WebElement
	 * @return the (same) WebElement once it is clickable (visible and enabled)
	 */
	public WebElement waitForElementToBeClickable(Long timeOutInSeconds, Long sleepInMillis, WebElement element);
	
	
	/**
	 * Wait for a WebElement to appear
	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param locator the locator
	 * @return the WebElement once it appears
	 */
	public  WebElement waitForElementToBePresent(Long timeOutInSeconds, Long sleepInMillis, By locator);
	
	/**
	 * Wait for a WebElement to become visible
	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param element the WebElement
	 * @return the (same) WebElement once it is visible
	 */
	public  WebElement waitForElementToBeVisible(Long timeOutInSeconds, Long sleepInMillis, WebElement element);
	
	/**
	 * Wait for WebElements to become visible
	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param elements the WebElements	 
	 * @return the (same) WebElements once all are visible
	 */
	public  List<WebElement> waitForElementsToBeVisible(Long timeOutInSeconds, Long sleepInMillis, WebElement...elements);
	
	/**
	 * Wait for WebElements to become visible
	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param elements the WebElements	 
	 * @return the (same) WebElements once all are visible
	 */
	public  List<WebElement> waitForElementsToBeVisible(Long timeOutInSeconds, Long sleepInMillis, List<WebElement> elements);
	
	/**
	 * Wait for a dropdown's options to be populated
	 * 
	 * @param dropdownElement the dropdown
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param minNumberOfOptions the minimum number of populated options to wait for
	 * @return the dropdown's elements
	 */
	public  Boolean waitForOptionsToLoad(Select dropdownElement, Long timeOutInSeconds, Long sleepInMillis, Integer minNumberOfOptions);
	
	/**
	 * The url associated with the page object
	 * 
	 * @return the url
	 */
	public String getPageURL();
	
	/**
	 * Sets the Page URL for this PageObject
	 * 
	 * @param pageURL the page url for this PageObject
	 */
	public void setPageURL(String pageURL);
	
	/**
	 * Gets the WebDriver instance
	 * 
	 * @return the WebDriver
	 */
	public WebDriver getDriver();
	
	/**
	 * Sets the WebDriver
	 * 
	 * @param driver the WebDriver
	 */
	public void setDriver(WebDriver driver);
	
	/**
	 * Upload the file
	 * @param fileId the ID of the file upload element
	 * @param localFileDir Directory where the file is located
	 * @param fileName Name of the file
	 */
	public void uploadFile(String fileId, String localFileDir, String fileName);
	
	/**
	 * Clicks a button on a webpage. Used in places where WebElement.click() does not work.
	 * @param button the button
	 */
	public void clickButton(WebElement button);
}