package com.notsruht.taf.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test runner that will run all feature files in the config/testing/test-resources/features directory
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = "classpath:features",
		plugin = {"pretty", "summary", "html:build/cucumber-reports/allFeatures.html","junit:build/cucumber-reports/allFeatures.xml"}, 
		glue = {"com.notsruht.taf.config", "com.notsruht.taf.stepdefs", "com.notsruht.taf.hooks"}
		)		
public class AllFeaturesTestRunner {
	
}