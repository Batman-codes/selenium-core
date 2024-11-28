package com.testleaf.web.element;

import org.openqa.selenium.WebElement;

public class SeLinkImpl extends SeElementImpl implements Link{


	public SeLinkImpl(WebElement element) {
		super(element);
	}

	@Override
	public String getText() {
		return element.getText();
	}

	@Override
	public String getHref() {
		return element.getAttribute("href");
	}

	@Override
	public void click() {
		element.click();
	}


	
}
