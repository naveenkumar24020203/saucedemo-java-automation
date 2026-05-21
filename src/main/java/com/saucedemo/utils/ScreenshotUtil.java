package com.saucedemo.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {
    private ScreenshotUtil() {
    }

    public static String captureScreenshot(WebDriver driver, String scenarioName) {
        try {
            Path screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path destination = Path.of(System.getProperty("user.dir"), "screenshots", scenarioName + "_" + timestamp + ".png");
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot, destination);
            return destination.toAbsolutePath().toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage(), e);
        }
    }

    public static byte[] captureScreenshotAsBytes(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
