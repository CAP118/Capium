package com.Capium.Utilities;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelperClass {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
	private final static int TIMEOUT = 10;

	private HelperClass() {
		/** private constructor to prevent instantiation */
	}

	/** Setup driver based on browser name */
	public static void setUpDriver(String browser) {
		WebDriver drv = null;

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			drv = new ChromeDriver(options);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			drv = new FirefoxDriver();
			drv.manage().window().maximize();
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			drv = new EdgeDriver();
			drv.manage().window().maximize();
			break;

		default:
			throw new IllegalArgumentException("Browser not supported: " + browser);
		}

		drv.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
		driver.set(drv);
		wait.set(new WebDriverWait(drv, Duration.ofSeconds(TIMEOUT)));
	}

	/** Get driver for current thread */
	public static WebDriver getDriver() {
		return driver.get();
	}

	/** Get wait for current thread */
	public static WebDriverWait getWait() {
		return wait.get();
	}

	/** Open page URL */
	public static void openPage(String url) {
		getDriver().get(url);
	}

	/** Tear down */
	public static void tearDown() {
	    WebDriver drv = driver.get();
	    if (drv != null) {
	        try {
	            drv.quit();
	        } catch (Exception e) {
	            System.out.println("Error quitting driver: " + e.getMessage());
	        } finally {
	            driver.remove(); 
	            wait.remove();   
	        }
	    }
	}


	/** Scroll to element with js */
	public static void scrollIntoView(WebElement element) {
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});",
					element);
		} catch (Exception e) {
			System.out.println("Scroll failed: " + e.getMessage());
		}
	}

	/** Safe Click with wait */
	public static void safeClick(WebElement element, String logMessage) {
		WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
		scrollIntoView(element);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	/** Safe SendKeys with clear */
	public static void safeType(WebElement element, String text) {
		WebDriver driver = getDriver();
		waitForPageToLoad(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		WebElement el = wait.until(ExpectedConditions.visibilityOf(element));
		el.clear();
		el.sendKeys(text);
	}

	/** Get text safely */
	public static String safeGetText(WebElement element, String elementName) {
		try {
			getWait().until(ExpectedConditions.visibilityOf(element));
			String text = element.getText().trim();
			System.out.println("Fetched text from " + elementName + ": " + text);
			return text;
		} catch (Exception e) {
			System.out.println("Failed to get text from: " + elementName + " -> " + e.getMessage());
			throw e;
		}
	}

	/** Wait until element is visible */
	public static WebElement waitForVisibility(WebElement element) {
		return getWait().until(ExpectedConditions.visibilityOf(element));
	}

	/** Wait until element is clickable */
	public static WebElement waitForClickable(WebElement element) {
		return getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

	/** Wait for Angular loader */
	public static void waitForPageToLoad(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			wait.until(webDriver -> (Boolean) js.executeScript("return (window.angular !== undefined) && "
					+ "(angular.element(document).injector() !== undefined) && "
					+ "(angular.element(document).injector().get('$http').pendingRequests.length === 0);"));
		} catch (Exception e) {
			Log.warn("Angular wait skipped: " + e.getMessage());
		}

		wait.until(webDriver -> js.executeScript("return document.readyState").toString().equals("complete"));
	}

	/** Wait for element visible */
	public static boolean isElementVisible(WebElement element, int timeoutSec) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSec));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/** Retry click for multiple times */
	public static void clickWithRetry(WebElement element, WebDriver driver, WebDriverWait wait) {
		int attempts = 0;
		while (attempts < 3) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				return;
			} catch (ElementClickInterceptedException | StaleElementReferenceException e) {
				System.out.println("Retry click #" + (attempts + 1) + " for: " + element);
				attempts++;
				HelperClass.sleep1(500);
			}
		}
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			System.out.println("Fallback: JavaScript click used.");
		} catch (Exception jsEx) {
			System.out.println("JS click failed: " + jsEx.getMessage());
		}
	}

	/** Sleep time */
	public static void sleep1(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
