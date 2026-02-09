package com.example.automation.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import static com.example.automation.configuration.ImportExportResultsToXray.remonteeXray;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.example.automation.steps",
                "com.example.automation.utils",
                "com.example.automation.configuration"
        },
        plugin = { "pretty", "html:target/cucumber-reports","json:target/cucumber.json" },
        monochrome = true,
        tags = "@POEI2-654"
)
public class TestRunner {

    /*
    @AfterClass
    public static void myTest() throws IOException, NoSuchAlgorithmException, KeyManagementException, KeyStoreException, InterruptedException {
        remonteeXray();
    }*/
}

