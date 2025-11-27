package com.Capium.Stepdefination;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.Capium.Actions.Login_Actions;
import com.Capium.Utilities.HelperClass;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	WebDriver driver=HelperClass.getDriver();

    @Before
    public void beforeScenario() {
        String browser = System.getProperty("browserType", "chrome"); 
        System.out.println("Launching browser from Hooks: " + browser);
        HelperClass.setUpDriver(browser);
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failed Test");
            }
            new Login_Actions().Signout(); 
        } catch (Exception e) {
            System.out.println("Error in scenario teardown: " + e.getMessage());
        } finally {
            HelperClass.tearDown();  
        }
    }


    @AfterAll
    public static void writeEndOfTestMarker() {
        String logFilePath = "logs/test-execution.log";
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String separator = "\n======================================================================================\n"
                + "TEST RUN COMPLETED at " + timestamp + "\n"
                + "======================================================================================\n";

        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(separator);
            System.out.println("End-of-test marker written to log file.");
        } catch (IOException e) {
            System.err.println("Failed to write end-of-test marker: " + e.getMessage());
        }
    }
}
