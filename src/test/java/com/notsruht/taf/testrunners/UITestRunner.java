package com.notsruht.taf.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test runner that will run all features and scenarios tagged with <b>"{@literal @}ui"</b>.
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = "classpath:features",
		plugin = {"pretty", "summary", "html:build/cucumber-reports/uiFeatures.html","junit:build/cucumber-reports/uiFeatures.xml"}, 
		glue = {"com.notsruht.taf.config", "com.notsruht.taf.stepdefs", "com.notsruht.taf.hooks"},
		tags = "@ui"
		)
public class UITestRunner {

}
