package com.testleaf.web.element;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class SeDropdownImpl extends SeElementImpl implements Dropdown{

    Select drop;
    public SeDropdownImpl(WebElement element) {
        super(element);
        drop = new Select(element);
    }

    @Override
    public void selectOptionByVisibleText(String optionName) {
        drop.selectByVisibleText(optionName);
    }

    @Override
    public void selectOptionByValue(String optionValue) {
        drop.selectByValue(optionValue);
    }

    @Override
    public void selectOptionByIndex(int index) {
        drop.selectByIndex(index);
    }

    @Override
    public List<Element> getAllOptions() {
        List<WebElement> allOptions = drop.getOptions();
        List<Element> options = new LinkedList<>();
        for(WebElement element : allOptions){
            options.add(new SeElementImpl(element));
        }
        return options;
    }

    @Override
    public boolean isMultiselect() {
        return drop.isMultiple();
    }
}
