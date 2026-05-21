package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(css = ".summary_total_label")
    private WebElement totalLabel;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public String getTotalPrice() {
        return getText(totalLabel);
    }

    public void clickFinish() {
        click(finishButton);
    }
}
