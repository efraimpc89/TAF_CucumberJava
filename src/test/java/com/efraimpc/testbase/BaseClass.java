package com.efraimpc.testbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.efraimpc.utils.ConfigsReader;
import com.efraimpc.utils.Constants;

public class BaseClass {
	
	public static WebDriver driver;
	
	/**
	 * Gets a a driver with the configuration specified on configuration.properties file.
	 * @return
	 */
	public static WebDriver setUp() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILE_PATH);
		
		switch(ConfigsReader.getProperty("browser").toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_PATH);
			driver = new FirefoxDriver();
			break;
		//More browsers can be added here as needed
		default:
			throw new RuntimeException("Browser is not supported");
		}
		
		//implicit wait setup
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		//maximize window
		driver.manage().window().maximize();
		//get to url specified on configs file
		driver.get(ConfigsReader.getProperty("url"));
		
		// initialize all elements on the pages package
		PageInitializer.initialize();
		
		return driver;
	}
	
	/**
	 * shutdowns driver
	 */
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
			
	}
	
}
