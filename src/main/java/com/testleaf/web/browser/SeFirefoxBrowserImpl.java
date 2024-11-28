package com.testleaf.web.browser;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class SeFirefoxBrowserImpl {
	
	private static Logger logger = Logger.getLogger(SeFirefoxBrowserImpl.class.getName());

	public WebDriver launchBrowser(Object capabilities) {
		
		FirefoxOptions firefoxOptions;
		
		if(capabilities instanceof FirefoxOptions) {
			
			firefoxOptions = (FirefoxOptions) capabilities;
		}else {
			logger.warning("Warning: Capabilities provided were incorrect for Firefox Capabilities. Creating driver with default capabilities");
			firefoxOptions = new FirefoxOptions();
		}
		
		return new FirefoxDriver(firefoxOptions);
	}

}
