package com.testleaf.web.element;

import org.openqa.selenium.WebElement;

public class SeButtonImpl  extends SeElementImpl implements Button{


	public SeButtonImpl(WebElement element) {
		super(element);
	}

	@Override
	public void click() {
		element.click();
	}


	
}
