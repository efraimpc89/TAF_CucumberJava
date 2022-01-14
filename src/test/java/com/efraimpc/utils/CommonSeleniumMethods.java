package com.efraimpc.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.efraimpc.testbase.PageInitializer;

public class CommonSeleniumMethods extends PageInitializer{
	
	/**
	 * Clears textbox and sends another text
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * Checks if Checkbox/radio is enabled and then clicks on the specified value
	 * @param listElement - List of Checkbox or radio buttons
	 * @param value - the value we want to check/select
	 */
	public static void clickRadioOrCheckbox(List<WebElement> listElement, String value) {
		String actualValue;
		
		for(WebElement element: listElement) {
			actualValue = element.getAttribute("value").trim();
			if(element.isEnabled() && actualValue.equals(value)) {
				element.click();
				break;
			}
		}
	}
	
	/**
	 * Checks if text is found in the dropdown element and if so it selects it
	 * @param element
	 * @param textToSelect
	 */
	public static void selectDropdown(WebElement element, String textToSelect) {
		try {
			Select select = new Select(element);
			
			List<WebElement> options = select.getOptions();
			
			for(WebElement el : options) {
				if(el.getText().equals(textToSelect)) {
					select.selectByVisibleText(textToSelect);
					break;
				}
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Send text to an alert. No AlertPresentException is handled if no alert was present.
	 * @param text
	 */
	public static void sendAlertText(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * switches to a frame by name or id. NoSuchFrameException is handled.
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			driver.switchTo().frame(nameOrId);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * switches to a frame by index. NoSuchFrameException is handled.
	 * @param index
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Selects a date from an opened calendar
	 * @param elements - days from the opened calendar
	 * @param text - the day to select
	 */
	 public static void selectCalendarDate(List<WebElement> elements, String text) {
		 for (WebElement day : elements) {
			 if(day.isEnabled()) {
				 if(day.getText().equals(text)) {
					 day.click();
					 break;
				 }
			 }
		 }
	 }
	 
	 
	 /**
	  * Takes a picture and saves it on screenshots folder
	  * @param fileName
	  * @return
	  */
	 public static byte[] takeScreenshot (String fileName) {
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 
		 byte[] pictureBytes = ts.getScreenshotAs(OutputType.BYTES);
		 
		 File file = ts.getScreenshotAs(OutputType.FILE);
		 
		 String destination = Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp() + ".jpg";
		 
		 try {
			 FileUtils.copyFile(file, new File(destination));
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		 return pictureBytes;
	 }
	 
	 /**
	  * returns current time stamp on a String
	  * @return
	  */
	 public static String getTimeStamp() {
		 Date date = new Date();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		 
		 return simpleDateFormat.format(date.getTime());
	 }
	 
	 
	 /**
	  * creates a WebDriverWait object and returns it
	  * @return
	  */
	 public static WebDriverWait getWaitObject() {
		 WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
		 
		 return wait;
	 }
	 
	 /**
	  * waits for a web element to be clickable and then returns it
	  * @param element
	  * @return
	  */
	 public static WebElement waitForElementToBeClickable(WebElement element) {
		 return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	 }
	 
	 /**
	  * waits for a web element to be visible and then returns it
	  * @param element
	  * @return
	  */
	 public static WebElement waitForElementToBeVisible(WebElement element) {
		 return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	 }
	 
	 /**
	  * Holds the execution for the given seconds. Mostly for debug purposes. 
	  * 
	  * @param seconds
	  */
	 public static void wait(int seconds) {
		 try {
			 Thread.sleep(seconds * 1000);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 /**
	  * Returns the driver casted into a JavascriptExecutor.
	  * @return
	  */
	 public static JavascriptExecutor getJSObject() {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 
		 return js;
	 }
	 
	 /**
	  * Clicks on an element with JavascriptExecutor object
	  */
	 public static void jsClick(WebElement element){
		 getJSObject().executeScript("arguments[0].click()", element);
	 }
	 
	 /**
	  * Scrolls to element with JavascriptExecutor object
	  * @param element
	  */
	 public static void scrollToElement(WebElement element){
		 getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	 }
	 
	 /**
	  * Scrolls down the passed pixel parameter with JavascriptExecutor object
	  * @param pixels - number of pixels to scroll down
	  */
	 public static void scrollDown(int pixels){
		 getJSObject().executeScript("window.scrollBy(0,"+pixels+")");
	 }
	 
	 /**
	  * Scrolls up the passed pixel parameter with JavascriptExecutor object
	  * @param pixels - number of pixels to scroll up
	  */
	 public static void scrollUp(int pixels){
		 getJSObject().executeScript("window.scrollBy(0,-"+pixels+")");
	 }
	 
	 
	 

}
