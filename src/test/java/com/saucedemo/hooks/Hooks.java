package com.saucedemo.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.ExtentManager;
import com.saucedemo.utils.ExtentTestManager;
import com.saucedemo.utils.LoggerHelper;
import com.saucedemo.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

public class Hooks {
    private static final Logger logger = LoggerHelper.getLogger(Hooks.class);
    private static final ExtentReports extent = ExtentManager.getExtentReports();

    @Before
    public void setUp(Scenario scenario) {
        DriverManager.initDriver();
        WebDriver driver = DriverManager.getDriver();
        ExtentTest extentTest = extent.createTest(scenario.getName());
        ExtentTestManager.setTest(extentTest);
        logger.info("Starting scenario: {}", scenario.getName());

        // open application between scenarios if needed
        new LoginPage(driver).openUrl(System.getProperty("BASE_URL"));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (scenario.isFailed()) {
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, scenario.getName().replaceAll("[^a-zA-Z0-9_-]", "_"));
            scenario.attach(ScreenshotUtil.captureScreenshotAsBytes(driver), "image/png", "failed-screenshot");
            ExtentTestManager.getTest().fail("Scenario failed: " + scenario.getName()).addScreenCaptureFromPath(screenshotPath);
        }
        DriverManager.quitDriver();
        logger.info("Finished scenario: {}", scenario.getName());
    }

    @AfterAll
    public static void cleanUp() {
        extent.flush();
        logger.info("Extent report generated");
    }
}
