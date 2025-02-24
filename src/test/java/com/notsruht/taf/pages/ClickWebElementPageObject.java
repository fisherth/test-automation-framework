package com.notsruht.taf.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.notsruht.taf.ui.pages.BasePageObject;
import com.notsruht.taf.ui.pages.TAFPageObject;

@TAFPageObject
public class ClickWebElementPageObject extends BasePageObject {
	private static final String ULTIMATE_QA = "https://ultimateqa.com/simple-html-elements-for-automation/";
	private static final String BUTTON_ID = "idExample";
	private static final String BUTTON_CLASS = "buttonClass";
	private static final String BUTTON_NAME = "button1";
	private static final String BUTTON_SUCCESS_PAGE_TITLE = "Button success - Ultimate QA";
	private WebElement webElement;
	
	public ClickWebElementPageObject(WebDriver tafWebDriver) {
		super(tafWebDriver);		
	}
	
	public void setButtonById() {
		webElement = findWebElementById(BUTTON_ID);
	}
	
	public void setButtonByClassname() {
		webElement = findWebElementByClass(BUTTON_CLASS);
	}
	
	public void setButtonByName() {
		webElement = findWebElementByName(BUTTON_NAME);
	}

	public Boolean clickButtonSuccess() {
		webElement.click();
		
		return BUTTON_SUCCESS_PAGE_TITLE.equals(getDriver().getTitle());
	}
	
	public void openUltimateQA() {
		getDriver().get(ULTIMATE_QA);
	}
}