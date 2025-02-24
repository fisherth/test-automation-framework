package com.notsruht.taf.ui;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class to find web elements on a page
 *
 */
public class WebElementLocatorUtil {
	
	/**
	 * Find web element by ID
	 * @param driver the WebDriver
	 * @param id the ID of the web element
	 * @return the web element
	 */
	public static WebElement findWebElementById(WebDriver driver, String id) {
		return driver.findElement(By.id(id));
	}
	
	/**
	 * Find web element by the specified locator
	 * 
	 * @param driver the WebDriver
	 * @param locatorType the locator used to find the web elements
	 * @return the web element
	 */
	public static WebElement findWebElement(WebDriver driver, By locatorType) {
		return driver.findElement(locatorType);
	}

	/**
	 * Find web elements by the specified locator
	 * 
	 * @param driver the WebDriver
	 * @param locatorType the locator used to find the web elements
	 * @return the web element
	 */
	public static List<WebElement> findWebElements(WebDriver driver, By locatorType) {
		return driver.findElements(locatorType);
	}	

	/**
	 * Find web element by the name attribute. If multiple elements have the same name, then this method
	 * will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param name The value of the element's name attribute
	 * @return the web element
	 */
	public static WebElement findWebElementByName(WebDriver driver, String name) {
		return findWebElement(driver, By.name(name));
	}

	/**
	 * Find web element by the class attribute. If multiple elements have the same class, then this method
	 * will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param className The value of the element's class attribute
	 * @return the web element
	 */
	public static WebElement findWebElementByClass(WebDriver driver, String className) {
		return findWebElement(driver, By.className(className));
	}

	/**
	 * Find web element by hyperlink text. If multiple hyperlinks with the same texts are present on the page, then this method
	 * will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param linkText The hyperlink text
	 * @return the web element
	 */
	public static WebElement findWebElementByLinkText(WebDriver driver, String linkText) {
		return findWebElement(driver, By.linkText(linkText));
	}

	/**
	 * Find web element by partial hyperlink text. If multiple hyperlinks with the same partial texts are present on the page, then this method
	 * will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param partialLinkText The partial hyperlink text
	 * @return the web element
	 */
	public static WebElement findWebElementByPartialLinkText(WebDriver driver, String partialLinkText) {
		return findWebElement(driver, By.partialLinkText(partialLinkText));
	}

	/**
	 * Find web element by tag name. If multiple tags with the same name are present on the page, then this method
	 * will always choose the first one. 
	 * 
	 * @param driver the WebDriver
	 * @param tagname The tag name
	 * @return the web element
	 */
	public static WebElement findWebElementByTagname(WebDriver driver, String tagname) {
		return findWebElement(driver, By.tagName(tagname));
	}

	/**
	 * Find web element by CSS selector. If the CSS selector matches more than one element, then this method will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param cssSelector The CSS selector
	 * @return the web element
	 */
	public static WebElement findWebElementByCssSelector(WebDriver driver, String cssSelector) {
		return findWebElement(driver, By.cssSelector(cssSelector));
	}

	/**
	 * Find web element by Xpath expression. If the XPath expression matches more than one element, then this method will always choose the first one.
	 * 
	 * @param driver the WebDriver
	 * @param xpath
	 * @return the web element
	 */
	public static WebElement findWebElementByXpath(WebDriver driver, String xpath) {
		return findWebElement(driver, By.xpath(xpath));
	}

	/**
	 * Find web elements by the name attribute.
	 * 
	 * @param driver the WebDriver
	 * @param name The value of the element's name attribute
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByName(WebDriver driver, String name) {
		return findWebElements(driver, By.name(name));
	}

	/**
	 * Find web elements by the class attribute.
	 * 
	 * @param driver the WebDriver
	 * @param className The value of the element's class attribute
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByClass(WebDriver driver, String className) {
		return findWebElements(driver, By.className(className));
	}

	/**
	 * Find web elements by hyperlink text.
	 * 
	 * @param driver the WebDriver
	 * @param linkText The hyperlink text
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByLinkText(WebDriver driver, String linkText) {
		return findWebElements(driver, By.linkText(linkText));
	}

	/**
	 * Find web elements by partial hyperlink text.
	 * 
	 * @param driver the WebDriver
	 * @param partialLinkText The partial hyperlink text
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByPartialLinkText(WebDriver driver, String partialLinkText) {
		return findWebElements(driver, By.partialLinkText(partialLinkText));
	}

	/**
	 * Find web elements by tag name.
	 * 
	 * @param driver the WebDriver
	 * @param tagname The tag name
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByTagname(WebDriver driver, String tagname) {
		return findWebElements(driver, By.tagName(tagname));
	}

	/**
	 * Find web elements by CSS selector.
	 * 
	 * @param driver the WebDriver
	 * @param cssSelector The CSS selector
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByCssSelector(WebDriver driver, String cssSelector) {
		return findWebElements(driver, By.cssSelector(cssSelector));
	}

	/**
	 * Find web elements by Xpath expression.
	 * 
	 * @param driver the WebDriver
	 * @param xpath The XPath expression
	 * @return the web elements
	 */
	public static List<WebElement> findWebElementsByXpath(WebDriver driver, String xpath) {
		return findWebElements(driver, By.xpath(xpath));
	}
	
	/**
	 * Wait for a WebElement to become visible and clickable
	 * 
	 * @param driver the WebDriver	 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param element the WebElement
	 * @return the (same) WebElement once it is clickable (visible and enabled)
	 */
	public static WebElement waitForElementToBeClickable(WebDriver driver, Long timeOutInSeconds, Long sleepInMillis, WebElement element) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * Wait for a WebElement to appear
	 * 
	 * @param driver the WebDriver
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param locator the locator
	 * @return the WebElement once it appears
	 */
	public static WebElement waitForElementToBePresent(WebDriver driver, Long timeOutInSeconds, Long sleepInMillis, By locator) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis)).until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * Wait for a WebElement to become visible
	 * 
	 * @param driver the WebDriver	 * 
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param element the WebElement
	 * @return the (same) WebElement once it is visible
	 */
	public static WebElement waitForElementToBeVisible(WebDriver driver, Long timeOutInSeconds, Long sleepInMillis, WebElement element) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis)).until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * Wait for WebElements to become visible
	 * 
	 * @param driver the WebDriver
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param elements the WebElements	 
	 * @return the (same) WebElements once all are visible
	 */
	public static List<WebElement> waitForElementsToBeVisible(WebDriver driver, Long timeOutInSeconds, Long sleepInMillis, WebElement...elements) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis)).until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	/**
	 * Wait for WebElements to become visible
	 * 
	 * @param driver the WebDriver
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param elements the WebElements	 
	 * @return the (same) WebElements once all are visible
	 */
	public static List<WebElement> waitForElementsToBeVisible(WebDriver driver, Long timeOutInSeconds, Long sleepInMillis, List<WebElement> elements) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds), Duration.ofMillis(sleepInMillis)).until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	/**
	 * Wait for a dropdown's options to be populated
	 * 
	 * @param dropdownElement the dropdown
	 * @param timeOutInSeconds the timeout in seconds to wait
	 * @param sleepInMillis the duration in milliseconds to sleep between polls
	 * @param minNumberOfOptions the minimum number of populated options to wait for
	 * @return the dropdown's elements
	 */
	public static Boolean waitForOptionsToLoad(Select dropdownElement, Long timeOutInSeconds, Long sleepInMillis, Integer minNumberOfOptions) {
		FluentWait<Select> waiter = new FluentWait<Select>(dropdownElement)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofSeconds(sleepInMillis))
				.ignoring(NoSuchElementException.class);
		
		return waiter.until(select -> select.getOptions().size() >= minNumberOfOptions);		
	}	
}