package com.notsruht.taf.config;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.notsruht.taf.ui.WebDriverFactory;
import com.notsruht.taf.ui.pages.TAFPageObjectBeanPostProcessor;

/**
 * Class responsible for providing Spring managed beans for use in the Test Automation Framework
 *
 */
@Configuration
@ComponentScan(basePackages="com.notsruht.taf")
public class TAFSpringConfiguration {
	
		
	/**
	 * Spring managed web driver instance for use in Cucumber scenarios
	 * @return the web driver
	 */
	@Bean(name="tafWebDriver", destroyMethod="quit")
	@Lazy
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public RemoteWebDriver webDriver() {
		return (RemoteWebDriver)WebDriverFactory.getWebDriver();
	}
	
		
	/**
	 * Spring managed bean to locate web elements on a Page Object 
	 * @return the TAF Page Object Bean Post Processor
	 */
	@Bean(name="tAFPageObjectBeanPostProcessor")
	@Lazy
	public TAFPageObjectBeanPostProcessor tAFPageObjectBeanPostProcessor() {
		return new TAFPageObjectBeanPostProcessor();
	}
}