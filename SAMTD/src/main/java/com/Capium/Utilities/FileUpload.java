package com.Capium.Utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUpload {

	/**
     * Uploads a file when <input type="file"> is present
     * @param uploadElement The file input WebElement
     * @param filePath The full file path
     */
    public static void uploadUsingSendKeys(WebElement uploadElement, String filePath) {
        uploadElement.sendKeys(filePath);
        System.out.println("File uploaded using sendKeys: " + filePath);
    }
    /**
     * Uploads a file using Robot (works for OS-level dialogs)
     * @param filePath The full file path
     */
//    public static void uploadUsingRobot(String filePath) {
//        try {
//            StringSelection selection = new StringSelection(filePath);
//            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
//
//            Robot robot = new Robot();
//
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_V);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//
//            System.out.println("File uploaded using Robot: " + filePath);
//
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//    }
    public static void uploadExcelUsingRobot(String excelFilePath) {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(1000);

            StringSelection selection = new StringSelection(excelFilePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void uploadFile(WebDriver driver, String fileUploadButtonXpath, String filePath) throws Exception {
        WebElement uploadButton = driver.findElement(By.xpath(fileUploadButtonXpath));
        uploadButton.click();
        Thread.sleep(2000);
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        Robot robot = new Robot();
        robot.delay(500);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

