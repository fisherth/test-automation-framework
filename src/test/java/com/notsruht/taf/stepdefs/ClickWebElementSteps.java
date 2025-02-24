package com.notsruht.taf.stepdefs;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.notsruht.taf.pages.ClickWebElementPageObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClickWebElementSteps {
	private static final Logger LOG = LogManager.getLogger(ClickWebElementSteps.class);

		
	@Autowired
	private ClickWebElementPageObject clickWebElementPageObject;	
	
	@Given ("access to ultimateqa.com")
	public void accessUltimateQA() {
		clickWebElementPageObject.openUltimateQA();
	}
	
	@When("the user locates a button by ID")
    public void locateButtonById() {
		clickWebElementPageObject.setButtonById();
    }
	
	@When("the user locates a button by classname")
    public void locateButtonByClassname() {
		clickWebElementPageObject.setButtonByClassname();
    }
	
	@When("the user locates a button by name")
    public void locateButtonByName() {
		clickWebElementPageObject.setButtonByName();
    }
	
	@Then("it can be clicked successfully") 
	public void clickButton() {
		assertTrue(clickWebElementPageObject.clickButtonSuccess());
	}	
}