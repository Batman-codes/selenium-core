package com.testleaf.web.browser;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.testleaf.constants.BrowserTypes;

public class SeDriverObjectPoolImpl{
	
	private static final Logger logger = Logger.getLogger(SeDriverObjectPoolImpl.class.getName());
	
	private static final ConcurrentMap<BrowserCapabilitiesKey, BlockingQueue<WebDriver>> driverCapabilityMap = new ConcurrentHashMap<>();
	private static final ConcurrentMap<WebDriver, BrowserCapabilitiesKey> driverBrowserCapabilityMap = new ConcurrentHashMap<>();
	
	public static WebDriver getBrowserFromPool(BrowserTypes browserType, Object capabilities) {
		
		logger.info("Checking if driver with given capabilities exist in the pool");
		
		BrowserCapabilitiesKey key = new BrowserCapabilitiesKey(browserType, (Capabilities) capabilities);
		
		BlockingQueue<WebDriver> driverQueue = driverCapabilityMap.computeIfAbsent(key, k -> new LinkedBlockingQueue());
		
		WebDriver driver = driverQueue.poll();
		
		if(driver == null) {
			logger.info("Driver with given Capabilities : " + capabilities.toString() + " and BrowserType : " + browserType + " is not present in the pool.Hence creating a new one");
			
			driver = SeBrowserFactoryImpl.getInstance(browserType, capabilities);
			
			logger.info("Adding the new browser instance to driverBrowsermap to use it for releasing the browser");
			driverBrowserCapabilityMap.put(driver, key);
		}else {
			logger.info("Re-using the browser");
		}
		
		return driver;
		
	}

	public static void releaseBrowserToPool(Object browser) {
		BrowserCapabilitiesKey key;
		if(browser instanceof WebDriver) {
			
			key = driverBrowserCapabilityMap.get(browser);
			if(key!=null) {
				driverCapabilityMap.computeIfAbsent(key, k -> new LinkedBlockingQueue<>()).offer((WebDriver)browser);
				logger.info("Released Driver -> " + browser.hashCode());
			}
			
		}else {
			logger.warning("Warning: the browser given is incorrect");
			
		}
		
	}

	public static void closeAllBrowsers() {
		
		for(WebDriver driver : driverBrowserCapabilityMap.keySet()) {
			
			driver.quit();
		}
		
	}
	
	

	
	

}
