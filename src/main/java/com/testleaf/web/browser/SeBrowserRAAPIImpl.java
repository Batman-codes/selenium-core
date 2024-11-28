package com.testleaf.web.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.testlead.web.api.RAAPIClientImpl;
import com.testleaf.constants.BrowserTypes;
import com.testleaf.constants.LocatorType;
import com.testleaf.web.element.Dropdown;
import com.testleaf.web.element.Edit;
import com.testleaf.web.element.Link;
import com.testleaf.web.element.SeButtonImpl;
import com.testleaf.web.element.SeDropdownImpl;
import com.testleaf.web.element.SeEditImpl;
import com.testleaf.web.element.SeElementImpl;
import com.testleaf.web.element.SeLinkImpl;

public class SeBrowserRAAPIImpl extends RAAPIClientImpl implements Browser{

	private WebDriver driver;
	
	public SeBrowserRAAPIImpl(BrowserTypes browserType){
		
		ChromeOptions option = new ChromeOptions();
		
		option.addArguments("--start-maximised");
		
		this.driver = SeDriverObjectPoolImpl.getBrowserFromPool(browserType, option);
		
	}
	
	
	@Override
	public void navigateTo(String url) {
		this.driver.get(url);
	}

	@Override
	public void maximize() {
		this.driver.manage().window().maximize();
	}

	@Override
	public void closeBrowser() {
		this.driver.close();
	}
	
	@Override
	public void releaseBrowser() {
		
		SeDriverObjectPoolImpl.releaseBrowserToPool(driver);
		
	}
	
	private WebElement findElement(LocatorType locatorType, String locator) {
		switch(locatorType) {
		case ID: return this.driver.findElement(By.id(locator));
		case NAME: return this.driver.findElement(By.name(locator));
		case CLASS: return this.driver.findElement(By.className(locator));
		case XPATH: return this.driver.findElement(By.xpath(locator));
		case LINKTEXT: return this.driver.findElement(By.linkText(locator));
		default: throw new IllegalArgumentException("Unsupported Locator Type :"+locatorType);
		}
	}

	@Override
	public SeElementImpl locateElement(LocatorType locatorType, String element) {
		return new SeElementImpl(findElement(locatorType, element));
	}

	@Override
	public Edit locateEdit(LocatorType locatorType, String element) {
		return new SeEditImpl(findElement(locatorType, element));
	}

	@Override
	public SeButtonImpl locateButton(LocatorType locatorType, String element) {
		return new SeButtonImpl(findElement(locatorType, element));
	}

	@Override
	public Link locateLink(LocatorType locatorType, String element) {
		return new SeLinkImpl(findElement(locatorType, element));

	}

	@Override
	public Dropdown locateDropdown(LocatorType locatorType, String element) {
		return new SeDropdownImpl(findElement(locatorType,element));
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

}
