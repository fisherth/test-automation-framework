package com.notsruht.taf.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

/**
 * Looks up the elements on Page Object classes with the @TAFPageObject annotation
 */
public class TAFPageObjectBeanPostProcessor implements BeanPostProcessor {
	private static final Logger LOG = LogManager.getLogger(TAFPageObjectBeanPostProcessor.class);
	private static final String WEBDRIVER_BEAN = "tafWebDriver";

	@Autowired
	private ApplicationContext context;
	

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean.getClass().isAnnotationPresent(TAFPageObject.class)) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("This is in Bean for page objects with annotation TAFPageObject");
			}
			
			RemoteWebDriver remoteDriver = (RemoteWebDriver)context.getBean(WEBDRIVER_BEAN);
			
			PageFactory.initElements(remoteDriver, bean);
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
}