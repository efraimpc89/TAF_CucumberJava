package com.efraimpc.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.efraimpc.testbase.BaseClass;

public class ProductsPage {

	@FindBy(xpath="//div[@class='app_logo']")
	public WebElement appLogo;
	
	public ProductsPage() {
		PageFactory.initElements(BaseClass.driver, this);
	}
}
