package com.testleaf.web.element;

import org.openqa.selenium.WebElement;

public class SeEditImpl extends SeElementImpl  implements Edit{


	public SeEditImpl(WebElement element) {
		super(element);
	}

	@Override
	public void type(String text) {
		element.sendKeys(text);
	}

	@Override
	public String getValue() {
		return element.getAttribute("value");
	}

	@Override
	public void clear() {
		element.clear();
	}

}
