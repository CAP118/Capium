package com.Capium.PracticeManagement.stepFiles;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Capium.PracticeManagement.Actions.Login_Actions;
import com.Capium.Utilities.ConfigReader;
import com.Capium.Utilities.HelperClass;
import com.Capium.Utilities.Log;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login_Stepdef {

	WebDriver driver = HelperClass.getDriver();
	WebDriverWait wait = HelperClass.getWait();
	
	Login_Actions login=new Login_Actions();

	@Given("Launch browser")
	public void launch_browser() {
		System.out.println("Browser Launched Before Start Scenario");
	}

	@When("Enter the application URL {string}")
	public void enter_the_application_url(String url) {
		url = ConfigReader.getProperty("Live.url");
		HelperClass.openPage(url);
		login.clickOnAccountant();
	}

	@When("Enter credentials in respective fields")
	public void enter_credentials_in_respective_fields() {
	    WebDriver driver = HelperClass.getDriver();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    String currentUrl = driver.getCurrentUrl().toLowerCase();

	    String username = "";
	    String password = "";

	    // Identify environment
	    if (currentUrl.contains("beta.capium.co.uk")) {
	        username = ConfigReader.getProperty("Beta.username");
	        password = ConfigReader.getProperty("Beta.password");
	        Log.info("Detected Beta Environment → Using Beta credentials");
	    } else if (currentUrl.contains("app.capium.com")) {
	        username = ConfigReader.getProperty("Live.username");
	        password = ConfigReader.getProperty("Live.password");
	        Log.info("Detected Live Environment → Using Live credentials");
	    } else if (currentUrl.contains("solo.capium.com")) {
	        username = ConfigReader.getProperty("Solo.username");
	        password = ConfigReader.getProperty("Solo.password");
	        Log.info("Detected Solo Environment → Using Solo credentials");
	    } else if (currentUrl.contains("clientportal.capium.com")) {
	        username = ConfigReader.getProperty("ClientPortal.username");
	        password = ConfigReader.getProperty("ClientPortal.password");
	        Log.info("Detected Client Portal Environment → Using Client Portal credentials");
	    } else {
	        throw new RuntimeException("Unknown environment: " + currentUrl);
	    }

	    // ---- Handle login type ----
	    try {
	        if (currentUrl.contains("beta.capium.co.uk") || currentUrl.contains("app.capium.com")) {
	            // Old login (used by both Live and Beta)
	            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtusername")));
	            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtpassword")));

	            usernameField.clear();
	            usernameField.sendKeys(username);

	            passwordField.clear();
	            passwordField.sendKeys(password);

	            Log.info("Entered credentials using OLD login locators (Live/Beta).");

	        } else {
	            // Identity login (Solo, ClientPortal, etc.)
	            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Username")));
	            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Password")));

	            usernameField.clear();
	            usernameField.sendKeys(username);

	            passwordField.clear();
	            passwordField.sendKeys(password);

	            Log.info("Entered credentials using IDENTITY login locators (Solo/ClientPortal).");
	        }

	        // Click Login button (common for all)
	        WebElement loginBtn =driver.findElement(By.xpath("//button[normalize-space()='Sign In' or normalize-space()='Login']"));
	        HelperClass.safeClick(loginBtn, "Click Login button");

	        Log.info("Clicked on Login button successfully.");

	    } catch (Exception e) {
	        Log.error("Error while entering credentials or clicking login: " + e.getMessage());
	        throw e;
	    }
	}

	@Then("Click on login Button")
	public void click_on_login_button() throws InterruptedException {
		HelperClass.getDriver().findElement(By.xpath("//button[@id='btnLogin']")).click();
		Thread.sleep(2000);
	}

	@Then("Navigate to {string} module")
	public void navigate_to_module(String moduleName) {
		login.NavigateToModule(moduleName);
	}

}
