package com.saucedemo.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {
    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reports/extent-report.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("SauceDemo Automation Report");
            sparkReporter.config().setReportName("SauceDemo Regression Results");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", ConfigReader.get("BASE_URL", "https://www.saucedemo.com"));
            extent.setSystemInfo("Browser", ConfigReader.get("BROWSER", "chrome"));
        }
        return extent;
    }
}
