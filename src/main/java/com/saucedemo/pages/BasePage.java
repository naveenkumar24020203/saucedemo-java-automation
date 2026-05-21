package com.saucedemo.pages;

import com.saucedemo.utils.WaitUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        WaitUtils.waitForClickable(driver, element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        WaitUtils.waitForVisibility(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        WaitUtils.waitForVisibility(driver, element);
        return element.getText().trim();
    }

    protected void scrollTo(WebElement element) {
        if (driver instanceof JavascriptExecutor js) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    protected void waitForPageLoad() {
        WaitUtils.waitForPageLoad(driver);
    }
}
