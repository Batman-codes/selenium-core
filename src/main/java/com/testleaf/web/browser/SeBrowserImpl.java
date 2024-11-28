package com.testleaf.web.browser;

import com.testleaf.web.element.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.testleaf.constants.LocatorType;

public class SeBrowserImpl implements Browser{

	private WebDriver driver;
	
	public SeBrowserImpl() {
		this.driver = new ChromeDriver();
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
		// TODO Auto-generated method stub
		
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
