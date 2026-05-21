package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderConfirmationPage extends BasePage {

    @FindBy(css = ".complete-header")
    private WebElement confirmationHeader;

    @FindBy(css = ".complete-text")
    private WebElement confirmationMessage;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationHeader() {
        return getText(confirmationHeader);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }
}
