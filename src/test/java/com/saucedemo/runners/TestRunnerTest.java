package com.saucedemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.saucedemo",
        plugin = {
                "pretty",
                "html:target/cucumber-html-report.html",
                "json:target/cucumber.json"
        },
        monochrome = true,
        publish = false
        // tags = "@checkout"
)
@Listeners({com.saucedemo.utils.RetryListener.class})
public class TestRunnerTest extends AbstractTestNGCucumberTests {
}

