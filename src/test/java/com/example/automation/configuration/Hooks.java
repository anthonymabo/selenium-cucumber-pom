package com.example.automation.configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After

    public void screenshot(Scenario scenarion) throws IOException {

        if (scenarion.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            File destination = new File("target/screeshots/" + scenarion.getName() + ".png");
            FileUtils.copyFile(source, destination);

            byte[]  screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenarion.attach(screenshot, "image/png", "Failure screenshot");
        }
    }

    public void tearDown() {
        DriverFactory.quitDriver();
    }



}
