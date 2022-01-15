package com.efraimpc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.efraimpc.testbase.BaseClass;

public class LoginPage {

	@FindBy(id="user-name")
	public WebElement txtUsername;
	
	@FindBy(id="password")
	public WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='login-button']")
	public WebElement btnLogin;
	
	@FindBy(xpath="//div[@class='error-message-container error']")
	public WebElement lblErrorMsg;
	
	public LoginPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
}
