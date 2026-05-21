package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".checkout_button")
    private WebElement checkoutButton;

    @FindBy(css = ".cart_item")
    private WebElement cartItem;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        String itemXPath = String.format("//div[@class='inventory_item_name' and text()='%s']", productName);
        return !driver.findElements(By.xpath(itemXPath)).isEmpty();
    }

    public void removeItemFromCart(String productName) {
        String removeXPath = String.format("//div[text()='%s']/ancestor::div[@class='cart_item']//button", productName);
        WebElement removeButton = driver.findElement(By.xpath(removeXPath));
        click(removeButton);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }
}
