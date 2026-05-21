package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".title")
    private WebElement productsTitle;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement menuButton;
    
    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logOutButton;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(productsTitle);
    }

    public void addProductToCart(String productName) {
        String buttonXPath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        WebElement addButton = driver.findElement(By.xpath(buttonXPath));
        click(addButton);
    }

    public void removeProductFromCart(String productName) {
        String buttonXPath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        WebElement removeButton = driver.findElement(By.xpath(buttonXPath));
        click(removeButton);
    }

    public void openCart() {
        click(cartLink);
    }

    public void openMenu() {
        click(menuButton);
    }

    public void clickLogOut() {
        click(logOutButton);
    }

    public int getCartBadgeCount() {
        try {
            WebElement badge = driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']//span[@data-test='shopping-cart-badge']"));
            String badgeText = getText(badge);
            return Integer.parseInt(badgeText.trim());
        } catch (Exception e) {
            return 0;
        }
    }
}
