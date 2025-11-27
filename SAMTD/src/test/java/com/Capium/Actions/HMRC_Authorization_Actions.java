package com.Capium.Actions;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Capium.Utilities.HelperClass;

public class HMRC_Authorization_Actions {

	WebDriver driver = HelperClass.getDriver();
	WebDriverWait wait = HelperClass.getWait();

	public HMRC_Authorization_Actions() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Manage']/parent::a")
	private static WebElement Manage;

	@FindBy(xpath = "(//span[normalize-space()='Add Client']/ancestor::button)[last()]")
	private static WebElement AddClient;

	@FindBy(xpath = "//input[@formcontrolname='fullName']")
	private static WebElement InputName;

	@FindBy(xpath = "//input[@formcontrolname='name']")
	private static WebElement ContactDetails_Name;

	@FindBy(xpath = "//input[@formcontrolname='email']")
	private static WebElement ContactDetails_Email;

	@FindBy(xpath = "//input[@formcontrolname='utrNo']")
	private static WebElement UTRNo;

	@FindBy(xpath = "//span[normalize-space()='Save and Continue']/parent::button")
	private static WebElement SaveandContinue;

	@FindBy(xpath = "//span[normalize-space()='HMRC Authorisation']/parent::button")
	private static WebElement HMRCAuthorisation;

	@FindBy(xpath = "//a[normalize-space(text())='Continue']")
	private static WebElement Continue;

	@FindBy(xpath = "//a[normalize-space(text())='Sign in to the HMRC online service']")
	private static WebElement Signinto_HMRC;

	@FindBy(xpath = "//a[@rel='noreferrer noopener']")
	private static WebElement DonthaveTestCredentials;

	@FindBy(xpath = "//label[@for='Individual']")
	private static WebElement Individual;

	@FindBy(xpath = "//button[@id='submit']")
	private static WebElement Create;

	@FindBy(xpath = "//li/p[contains(@id,'userid')]/following-sibling::p")
	private static WebElement UserID;

	@FindBy(xpath = "//li/p[contains(@id,'password')]/following-sibling::p")
	private static WebElement Password;

	@FindBy(xpath = "//li/p[contains(@id,'nino')]/following-sibling::p")
	private static WebElement NINO;

	@FindBy(xpath = "//input[@id='userId']")
	private static WebElement InputUserID;

	@FindBy(xpath = "//input[@id='password']")
	private static WebElement InputPassword;

	@FindBy(xpath = "//button[@id='submit']")
	private static WebElement SignIN;

	@FindBy(xpath = "//button[@id='givePermission']")
	private static WebElement GivePermission;

	@FindBy(xpath = "//input[@formcontrolname='insuranceNo']")
	private static WebElement InputNINO;

	String Parentwindow;

	public void ClickOnManageTab() {
		HelperClass.safeClick(Manage, "Click On manage tab");
	}

	public void ClickOnAddClinet() {
		HelperClass.safeClick(AddClient, "Add Client ");
	}

	 public static int clientCounter = 1;

	public void EnterAllMandatoryFields() {
		String suffix = String.format("%03d", clientCounter++);
		String dynamicClientName = "Test Client" + suffix;
		HelperClass.safeType(InputName, dynamicClientName);
		HelperClass.safeType(ContactDetails_Name, "Test Contact " + suffix);
		HelperClass.safeType(ContactDetails_Email, "testcontact" + suffix + "@gmail.com");
		HelperClass.safeType(UTRNo, "1234567890");
		HelperClass.safeType(InputNINO, "YE178193A");
		System.out.println("Entered Client Name: " + dynamicClientName);
	}

	public void ClickOnSaveAndContinue() {
		HelperClass.safeClick(SaveandContinue, "Clicked on Save and Continue");
	}

	public void ClickonHMRC_Authorization() {
		HelperClass.safeClick(HMRCAuthorisation, "Clicked on HMRC Authorization");
	}

	public void ClickOnContinue() {
		HelperClass.safeClick(Continue, "Click on Continue");
	}

	public void ClickOnSignINtoHMRC() {
		HelperClass.safeClick(Signinto_HMRC, "Click on Sign into HMRC");
	}

	public void ClickOnDonthaveTestCrendentials() {
		Parentwindow = driver.getWindowHandle();
		System.out.println("Parent Window: " + Parentwindow);

		HelperClass.safeClick(DonthaveTestCredentials, "Click on Don't Have Test Credentials");
	}

	public void SelectIndividualType() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(d -> d.getWindowHandles().size() > 1);
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println("All windows: " + allWindows);
		for (String currentWindow : allWindows) {
			if (!currentWindow.equals(Parentwindow)) {
				driver.switchTo().window(currentWindow);
				System.out.println("Switched to child window: " + currentWindow);
				HelperClass.scrollIntoView(Individual);
				HelperClass.clickWithRetry(Individual, driver, wait);
				HelperClass.safeClick(Create, "Click On Create");
				String UserId = HelperClass.safeGetText(UserID, "User ID").trim();
				String password = HelperClass.safeGetText(Password, "Password").trim();
				String NiNo = HelperClass.safeGetText(NINO, "Get NiNo number").trim();
				System.out.println("UserId: " + UserId + ", Password: " + password + ", NiNo: " + NiNo);
				driver.close();
				driver.switchTo().window(Parentwindow);
				System.out.println("Switched back to parent window: " + Parentwindow);
				HelperClass.safeType(InputUserID, UserId);
				HelperClass.safeType(InputPassword, password);
				HelperClass.safeClick(SignIN, "Click on SignIN");
				HelperClass.waitForVisibility(GivePermission);
				HelperClass.safeClick(GivePermission, "Click on Give Permission");
				HelperClass.waitForVisibility(InputNINO);
				HelperClass.safeType(InputNINO, NiNo);
				break;
			}
		}
	}

}
