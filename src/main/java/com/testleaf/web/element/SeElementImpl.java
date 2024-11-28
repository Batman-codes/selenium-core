package com.testleaf.web.element;

import org.openqa.selenium.WebElement;

public class SeElementImpl implements Element{
	
	protected WebElement element;
	
	public SeElementImpl(WebElement element) {
		this.element = element;
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	@Override
	public boolean isVisible() {
		return element.isDisplayed();
	}

	@Override
	public String getAttribute(String attribute) {
		return element.getAttribute(attribute);
	}

	@Override
	public String getText() {
		return element.getText();
	}


}
