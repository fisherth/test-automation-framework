package com.notsruht.taf.ui;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * Custom HtmlUnitDriver that does not throw Exceptions on Javascript errors.
 *
 */
public class TAFHtmlUnitDriver extends HtmlUnitDriver {

	public TAFHtmlUnitDriver() {
		
	}

	public TAFHtmlUnitDriver(boolean enableJavascript) {
		super(enableJavascript);
		
	}

	public TAFHtmlUnitDriver(BrowserVersion version) {
		super(version);
		
	}

	public TAFHtmlUnitDriver(Capabilities capabilities) {
		super(capabilities);
		
	}

	public TAFHtmlUnitDriver(BrowserVersion version, boolean enableJavascript) {
		super(version, enableJavascript);
		
	}

	public TAFHtmlUnitDriver(Capabilities desiredCapabilities, Capabilities requiredCapabilities) {
		super(desiredCapabilities, requiredCapabilities);
		
	}
	
	@Override
    protected WebClient modifyWebClient(WebClient client) {
        WebClient modifiedClient = super.modifyWebClient(client);
        modifiedClient.getOptions().setThrowExceptionOnScriptError(false);
        return modifiedClient;
    }
}
