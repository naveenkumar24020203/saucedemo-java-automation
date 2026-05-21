package com.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver() {
        String browser = ConfigReader.get("BROWSER", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfigReader.get("HEADLESS", "false"));

        switch (browser) {
            case "firefox" -> driver.set(createFirefoxDriver(headless));
            case "edge" -> driver.set(createEdgeDriver(headless));
            default -> driver.set(createChromeDriver(headless));
        }
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

private static WebDriver createChromeDriver(boolean headless) {
    WebDriverManager.chromedriver().setup();

    ChromeOptions options = new ChromeOptions();
    // options.addArguments("--headless=new");
    options.addArguments("--window-size=1920,1080");
    options.addArguments("--disable-notifications");
    options.addArguments("--disable-save-password-bubble");
    options.addArguments("--disable-password-generation");
    options.addArguments("--disable-features=PasswordManagerOnboarding,PasswordLeakDetection");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--incognito");

    // Required for GitHub Actions Linux runner
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(options);
}

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        if (headless) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1920,1080");
        if (headless) {
            options.addArguments("--headless=new");
        }
        return new EdgeDriver(options);
    }
}
