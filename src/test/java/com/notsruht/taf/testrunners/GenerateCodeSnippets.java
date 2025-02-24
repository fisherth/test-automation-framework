package com.notsruht.taf.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test runner that will print code snippets for undefined steps in a feature file.
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(	
		monochrome = true,
		features = "classpath:features/YourFeatureFile.feature",
		plugin = {"pretty", "summary", "html:build/cucumber-reports/singleFeature.html","junit:build/cucumber-reports/singleFeature.xml"}, 
		glue = {"com.notsruht.taf.config", "com.notsruht.taf.stepdefs", "com.notsruht.taf.hooks"},
		dryRun = true
		)
public class GenerateCodeSnippets {

}