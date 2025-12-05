package com.Capium.PracticeManagement.Actions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Capium.Utilities.HelperClass;
import com.Capium.Utilities.Log;

public class Login_Actions {

	WebDriver driver = HelperClass.getDriver();
	WebDriverWait wait = HelperClass.getWait();

	public Login_Actions() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//a[@aria-haspopup='menu'])[last()]")
	private static WebElement FivePoint_o_Profile;

	@FindBy(xpath = "//span[normalize-space()='Sign Out']/parent::a")
	private static WebElement FivePoint_o_Logout;

	@FindBy(xpath = "//a[@title='Logout']")
	private static WebElement ThreePoint_o_Logout;

	@FindBy(xpath = "//h6[normalize-space()='Accountant']/parent::div//a")
	private static WebElement AccountantLogin;

	@FindBy(xpath = "//a[@tooltip='Profile']")
	private static WebElement PM_MyAdmin_Profile;

	@FindBy(xpath = "//strong[normalize-space()='Logout']")
	private static WebElement PM_MyAdmin_Logout;

	@FindBy(xpath = "//a[@title='My Home']")
	private static WebElement NewEco_CIcon;

	@FindBy(xpath = "//span[normalize-space()='My Admin']/ancestor::a")
	private static WebElement MyAdmin_Module;
	
	@FindBy(xpath="//h6[normalize-space()='Accounts Production']/parent::a")
	private static WebElement NewEcoHome_AccountsProduction;
	
	@FindBy(xpath="//h6[normalize-space()='Bookkeeping']/parent::a")
	private static WebElement NewEcoHome_Bookkeeping;
	
	@FindBy(xpath="//h6[normalize-space()='Charity Accounts']/parent::a")
	private static WebElement NewEcoHome_CharityAccounts;
	
	@FindBy(xpath="//h6[normalize-space()='Corporation Tax']/parent::a")
	private static WebElement NewEcoHome_CorporationTax;
	
	@FindBy(xpath="//h6[normalize-space()='Payroll']/parent::a")
	private static WebElement NewEcoHome_Payroll;
	
	@FindBy(xpath="//h6[normalize-space()='Self Assessment']/parent::a")
	private static WebElement NewEcoHome_SelfAssessment;
	
	@FindBy(xpath="//h6[normalize-space()='Practice Management']/parent::a")
	private static WebElement NewEcoHome_Practicemanagement;
	
	@FindBy(xpath="//h6[normalize-space()='Time and Fees']/parent::a")
	private static WebElement NewEcoHome_TimeandFees;
	
	@FindBy(xpath="//h6[normalize-space()='365']/parent::a")
	private static WebElement NewEcoHome_365;
	
	@FindBy(xpath="//h6[normalize-space()='Capisign']/parent::a")
	private static WebElement NewEcoHome_Capisign;
	
	@FindBy(xpath="//h6[normalize-space()='Capium Hub']/parent::a")
	private static WebElement NewEcoHome_CapiumHub;
	
	@FindBy(xpath="//a[@title='Modules']")
	private static WebElement Insdie3_0_Module_Cicon;
	
	@FindBy(xpath="//a[@href='/' and @class='my-capium']")
	private static WebElement Inside3_0_Home;
	
	@FindBy(xpath="//a[@title='Modules']")
	private static WebElement Inside5_0_Cicon;
	
	@FindBy(xpath="//span[normalize-space()='Home']/ancestor::button")
	private static WebElement Inside5_0_Home;
	
	@FindBy(xpath = "//a[@href='/' and @class='my-capium']")
	private WebElement inside3_0_Home;   

	@FindBy(xpath = "//span[normalize-space()='Home']/ancestor::button")
	private WebElement inside5_0_Home;  

	@FindBy(xpath = "//span[@class='fa-stack module-img']//img[@alt='capium nav logo']")
	private WebElement pm_MyAdmin_CapiumHub_Cicon; 
	
	@FindBy(xpath="//span[contains(normalize-space(),'Home')]")
	private WebElement pm_MyAdmin_CapiumHub_Home;

	@FindBy(xpath = "//img[@src='assets/images/capium.png']")
	private WebElement capisign_Home; 

	@FindBy(xpath = "//a[@title='Modules']")
	private WebElement modulesIcon; 

	

	public void clickOnAccountant() {
		HelperClass.clickWithRetry(AccountantLogin, driver, wait);
	}

//	public void NavigateToModule(String Module) {
//		wait.until(d -> d.getCurrentUrl() != null && !d.getCurrentUrl().isEmpty());
//		String currentUrl = driver.getCurrentUrl();
//		System.out.println("Current URL: <<" + currentUrl + ">>");
//
//		boolean isBeta = currentUrl.contains("beta.capium.co.uk");
//		boolean isLive = currentUrl.contains("capium.com");
//		List<String> modulesUsingLastXpath = Arrays.asList("Corporation Tax", "Payroll", "Bookkeeping"); // add more if
//																											// needed
//		String moduleXpath;
//
//		if (modulesUsingLastXpath.contains(Module)) {
//			moduleXpath = "(//h6[normalize-space()='" + Module + "']/ancestor::div[@class='card'])[last()]";
//		} else {
//			moduleXpath = "//h6[normalize-space()='" + Module + "']/ancestor::div[@class='card']";
//		}
//
//		String homeUrl = isBeta ? "https://account.beta.capium.co.uk/" : "https://account.capium.com/home";
//		String oldEcoUrl = isBeta ? "https://app.beta.capium.co.uk/" : "https://app.capium.com/";
//
//		// ---------------- Case 1: Already in New Eco Home ----------------
//		if (currentUrl.contains(homeUrl)) {
//			try {
//				if (Module.equalsIgnoreCase("My Admin")) {
//					HelperClass.waitForVisibility(NewEco_CIcon);
//					HelperClass.safeClick(NewEco_CIcon, "Clicked on C-Icon (New Eco Home)");
//
//					HelperClass.waitForVisibility(MyAdmin_Module);
//					HelperClass.safeClick(MyAdmin_Module, "Clicked on My Admin Module (New Eco)");
//
//					Log.info("Successfully navigated to My Admin module in New Eco Home.");
//				} else {
//					WebElement targetModule = HelperClass.waitForVisibility(driver.findElement(By.xpath(moduleXpath)));
//					HelperClass.scrollIntoView(targetModule);
//					HelperClass.safeClick(targetModule, Module + " Module");
//				}
//			} catch (Exception e) {
//				Log.error("Error while navigating to module '" + Module + "' in New Eco Home: " + e.getMessage());
//			}
//			return;
//		}
//
//		// ---------------- Case 2: Old Eco Homepage ----------------
//		if (currentUrl.equals(oldEcoUrl) || currentUrl.equals(oldEcoUrl.replaceAll("/$", ""))) {
//			boolean navigated = false;
//			int retries = 3;
//			while (!navigated && retries-- > 0) {
//				try {
//					WebElement OldEcoCT = wait.until(ExpectedConditions
//							.visibilityOf(driver.findElement(By.xpath("//div[contains(text(),'Corporation Tax')]"))));
//					HelperClass.safeClick(OldEcoCT, "CT Module in Old Eco");
//
//					WebElement C_icon = wait.until(
//							ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Modules']"))));
//					HelperClass.safeClick(C_icon, "Modules Icon");
//
//					WebElement C_home = wait.until(ExpectedConditions
//							.visibilityOf(driver.findElement(By.xpath("//a[@href='/' and contains(@class,'my')]"))));
//					HelperClass.safeClick(C_home, "Home inside Modules");
//
//					String newUrl = driver.getCurrentUrl();
//					if (newUrl.contains(homeUrl)) {
//						navigated = true;
//						break;
//					}
//					System.out.println("Retry navigation → " + retries + " left");
//				} catch (Exception e) {
//					System.out.println("Exception in OldEco navigation: " + e.getMessage());
//				}
//			}
//			if (!navigated)
//				throw new RuntimeException("Failed to navigate to New Eco after retries");
//
//			WebElement targetModule = HelperClass.waitForVisibility(driver.findElement(By.xpath(moduleXpath)));
//			HelperClass.scrollIntoView(targetModule);
//			HelperClass.safeClick(targetModule, Module + " Module");
//			return;
//		}
//
//		// ---------------- Case 3: Already inside another module ----------------
//		if (currentUrl.startsWith(oldEcoUrl) && !currentUrl.equals(oldEcoUrl)) {
//			boolean navigated = false;
//			int retries = 3;
//			while (!navigated && retries-- > 0) {
//				try {
//					WebElement C_icon = wait.until(
//							ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Modules']"))));
//					HelperClass.safeClick(C_icon, "Modules Icon");
//
//					WebElement C_home = wait.until(ExpectedConditions
//							.visibilityOf(driver.findElement(By.xpath("//a[@href='/' and contains(@class,'my')]"))));
//					HelperClass.safeClick(C_home, "Home inside Modules");
//
//					String newUrl = driver.getCurrentUrl();
//					if (newUrl.contains(homeUrl)) {
//						navigated = true;
//						break;
//					} else if (newUrl.equals(oldEcoUrl) || newUrl.equals(oldEcoUrl.replaceAll("/$", ""))) {
//						WebElement OldEcoCT = wait.until(ExpectedConditions.visibilityOf(
//								driver.findElement(By.xpath("//div[contains(text(),'Corporation Tax')]"))));
//						HelperClass.safeClick(OldEcoCT, "CT Module in Old Eco");
//
//						WebElement inner_C_icon = wait.until(
//								ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Modules']"))));
//						HelperClass.safeClick(inner_C_icon, "Modules Icon Inside OldEco CT");
//
//						WebElement inner_C_home = wait.until(ExpectedConditions.visibilityOf(
//								driver.findElement(By.xpath("//a[@href='/' and contains(@class,'my')]"))));
//						HelperClass.safeClick(inner_C_home, "Home inside Modules after OldEco");
//
//						String finalUrl = driver.getCurrentUrl();
//						if (finalUrl.contains(homeUrl)) {
//							navigated = true;
//							break;
//						}
//					}
//				} catch (Exception e) {
//					System.out.println("Exception in Case 3 navigation: " + e.getMessage());
//				}
//			}
//			if (!navigated)
//				throw new RuntimeException("Failed to navigate to New Eco home after retries (Case 3).");
//
//			WebElement targetModule = HelperClass.waitForVisibility(driver.findElement(By.xpath(moduleXpath)));
//			HelperClass.scrollIntoView(targetModule);
//			HelperClass.safeClick(targetModule, Module + " Module");
//			return;
//		}
//
//		throw new RuntimeException("Unknown page state → Cannot navigate to module: " + Module);
//	}
	
	public void navigateToModule(String module) {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    String currentUrl = driver.getCurrentUrl().toLowerCase();

	    System.out.println("Navigating from: " + currentUrl + " --> To Module: " + module);

	    try {
	        navigateToHome(driver);  // Reuse your working HOME logic
	        String moduleXpath = "//h6[normalize-space()='" + module + "']/parent::a";
	        List<String> duplicatedModules = Arrays.asList("Bookkeeping", "Corporation Tax", "Payroll");
	        if (duplicatedModules.contains(module)) {
	            moduleXpath = "(" + moduleXpath + ")[last()]";
	        }

	        WebElement moduleCard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(moduleXpath)));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moduleCard);
	        moduleCard.click();

	        System.out.println("Successfully navigated to: " + module);

	    } catch (Exception e) {
	        System.out.println("Failed to navigate to module: " + module + " | Error: " + e.getMessage());
	        throw new RuntimeException(e);
	    }
	}


	public void Signout() {
		WebDriver driver = HelperClass.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.pollingEvery(Duration.ofMillis(300));

		boolean logoutClicked = false;

		try {
			String currentUrl = driver.getCurrentUrl();
			Log.info("Attempting logout from URL: " + currentUrl);

			boolean isBeta = currentUrl.contains(".capium.co.uk");
			boolean isLive = currentUrl.contains(".capium.com");

			boolean isNewEco = currentUrl.contains("365client") || currentUrl.contains("charity")
					|| currentUrl.contains("bookkeeping") || currentUrl.contains("samtd") || currentUrl.contains("ct")
					|| currentUrl.contains("clientportal");
			boolean isOldEco = currentUrl.contains("app.capium") || currentUrl.contains("app.beta.capium");

			Log.info("Environment: " + (isBeta ? "BETA" : isLive ? "LIVE" : "UNKNOWN"));
			Log.info("Layout: " + (isNewEco ? "NEW ECO" : isOldEco ? "OLD ECO" : "UNKNOWN"));

			// ---------------- CASE 1: New Eco ----------------
			if (isNewEco) {
				try {
					wait.until(ExpectedConditions.elementToBeClickable(FivePoint_o_Profile));
					HelperClass.clickWithRetry(FivePoint_o_Profile, driver, wait);
					Log.info("Clicked Profile icon (New Eco).");

					wait.until(ExpectedConditions.elementToBeClickable(FivePoint_o_Logout));
					HelperClass.clickWithRetry(FivePoint_o_Logout, driver, wait);
					Log.info("Clicked Logout option (New Eco).");
					logoutClicked = true;
				} catch (Exception e) {
					Log.error("Failed to logout from New Eco: " + e.getMessage());
				}
			}

			// ---------------- CASE 2: Old Eco (3.0 & 4.0 PM Modules) ----------------
			if (!logoutClicked && isOldEco) {
				try {
					if (HelperClass.isElementVisible(ThreePoint_o_Logout, 3)) {
						wait.until(ExpectedConditions.elementToBeClickable(ThreePoint_o_Logout));
						HelperClass.clickWithRetry(ThreePoint_o_Logout, driver, wait);
						Log.info("Clicked Logout (Old Eco 3.0).");
						logoutClicked = true;
					} else if (HelperClass.isElementVisible(PM_MyAdmin_Profile, 3)) {
						wait.until(ExpectedConditions.elementToBeClickable(PM_MyAdmin_Profile));
						HelperClass.clickWithRetry(PM_MyAdmin_Profile, driver, wait);
						Log.info("Clicked My Admin Profile (Old Eco 4.0 PM Module).");

						wait.until(ExpectedConditions.elementToBeClickable(PM_MyAdmin_Logout));
						HelperClass.clickWithRetry(PM_MyAdmin_Logout, driver, wait);
						Log.info("Clicked Logout (Old Eco 4.0 PM Module).");
						logoutClicked = true;
					}
				} catch (Exception e) {
					Log.error("Error during Old Eco logout: " + e.getMessage());
				}
			}

			// ---------------- VERIFY LOGOUT ----------------
			if (logoutClicked) {
				WebDriverWait loginWait = new WebDriverWait(driver, Duration.ofSeconds(15));
				loginWait.pollingEvery(Duration.ofMillis(300));
				loginWait.until(ExpectedConditions.or(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[normalize-space()='365']")),
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Sign In']")),
						ExpectedConditions.urlContains("login")));
				Log.info("Successfully logged out and redirected to login page.");
			} else {
				Log.warn("No logout element clicked — logout may have failed.");
				throw new RuntimeException("Logout failed → No logout path detected!");
			}

		} catch (Exception e) {
			Log.error("Signout failed: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void navigateToHome(WebDriver driver) {
	    String currentUrl = driver.getCurrentUrl().toLowerCase();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        if (currentUrl.contains("app.capium.com") || currentUrl.contains("selfassessment") 
	            || currentUrl.contains("accounts") || currentUrl.contains("payroll")) {
	            // 3.0 modules
	        	wait.until(ExpectedConditions.elementToBeClickable(Insdie3_0_Module_Cicon)).click();
	            wait.until(ExpectedConditions.elementToBeClickable(inside3_0_Home)).click();
	            System.out.println("Navigated to Home (3.0 Module)");

	        } else if (currentUrl.contains("appv4.capium.com") || currentUrl.contains("practice") 
	                   || currentUrl.contains("myadmin")) {
	            // PM, MYADMIN, CAPIUM HUB
	        	wait.until(ExpectedConditions.elementToBeClickable(pm_MyAdmin_CapiumHub_Cicon)).click();
	            wait.until(ExpectedConditions.elementToBeClickable(pm_MyAdmin_CapiumHub_Home)).click();
	            System.out.println("Navigated using C icon (PM/MyAdmin/Capium Hub)");

	        } else if (currentUrl.contains("capisign.capium.com")) {
	            // Capisign
	            wait.until(ExpectedConditions.elementToBeClickable(capisign_Home)).click();
	            System.out.println("Capisign - Navigated Home");

	        } else if (currentUrl.contains("bookkeeping.capium.com") || 
	                   currentUrl.contains("corporationtax.capium.com") || 
	                   currentUrl.contains("charity.capium.com") || 
	                   currentUrl.contains("timeandfees.capium.com") ||
	                   currentUrl.contains("clientportal.capium.com")) {

	            // Bookkeeping 5.0, CT5.0, Charity, Time & Fees, 365
	            wait.until(ExpectedConditions.elementToBeClickable(modulesIcon)).click();
	            wait.until(ExpectedConditions.elementToBeClickable(inside5_0_Home)).click();
	            System.out.println("Navigated Home (5.0 Module)");

	        } else {
	            System.out.println("No matching home navigation rule found for: " + currentUrl);
	        }

	    } catch (Exception e) {
	        System.out.println("Failed to click Home: " + e.getMessage());
	    }
	}

}
