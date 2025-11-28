package com.Capium.PracticeManagement.runFiles;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/Features/LoginPage.feature",
    glue = "com.Capium.PracticeManagement.stepFiles",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/CucumberReport.html",
        "json:target/cucumber-reports/CucumberReport.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
    },
    monochrome = true
)
public class Login_runner extends AbstractTestNGCucumberTests {

    
}
