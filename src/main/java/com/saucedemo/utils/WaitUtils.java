package com.saucedemo.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {
    private WaitUtils() {
    }

    public static WebDriverWait getWait(WebDriver driver) {
        int waitTime = ConfigReader.getInt("EXPLICIT_WAIT", 15);
        return new WebDriverWait(driver, Duration.ofSeconds(waitTime));
    }

    public static void waitForVisibility(WebDriver driver, WebElement element) {
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForClickable(WebDriver driver, WebElement element) {
        getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageLoad(WebDriver driver) {
        getWait(driver).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
