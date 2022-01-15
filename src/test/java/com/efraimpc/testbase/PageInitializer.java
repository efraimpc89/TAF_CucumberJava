package com.efraimpc.testbase;

import com.efraimpc.pages.LoginPage;
import com.efraimpc.pages.ProductsPage;

public class PageInitializer extends BaseClass{

	//Initializes every page 
	
	public static LoginPage loginPage;
	public static ProductsPage	productsPage;
	
	public static void initialize() {
		//Initialize Pages
		loginPage = new LoginPage();
		productsPage = new ProductsPage();
	}
	
}
