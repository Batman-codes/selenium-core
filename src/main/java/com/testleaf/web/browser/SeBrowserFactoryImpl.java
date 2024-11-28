package com.testleaf.web.browser;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.testleaf.constants.BrowserTypes;

public class SeBrowserFactoryImpl{
	
	private static final Logger logger = Logger.getLogger(SeBrowserFactoryImpl.class.getName());

	
	public static WebDriver getInstance(BrowserTypes browserType, Object capabilities) {
		
		switch(browserType) {
			case CHROME:
				logger.info("Getting new Chrome Browser");
				return (WebDriver) SeChromeBrowserImpl.launchBrowser(capabilities);
			case FIREFOX:
				logger.info("Getting new Firefox Browser");
				return (WebDriver) new SeFirefoxBrowserImpl().launchBrowser(capabilities);
			default:
				throw new IllegalArgumentException("Please provide correct browser name");

		}

	}
	

}
