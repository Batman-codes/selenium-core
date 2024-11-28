package com.testleaf.web.browser;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeChromeBrowserImpl{
	
	private static final Logger logger = Logger.getLogger(SeChromeBrowserImpl.class.getName());

	public static WebDriver launchBrowser(Object capabilities) {
		
		ChromeOptions chromeOptions;
		
		if(capabilities instanceof ChromeOptions) {
			
			chromeOptions = (ChromeOptions) capabilities;
			
		}else {
			logger.warning("Warning: Capabilities were incorrect for chrome capabilities. Creating new chromedriver with default capabilities");
			chromeOptions = new ChromeOptions();
		}
		return new ChromeDriver(chromeOptions);
	}
	
}
