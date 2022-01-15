package com.efraimpc.steps;

import static org.junit.Assert.assertTrue;

import com.efraimpc.utils.CommonSeleniumMethods;
import com.efraimpc.utils.ConfigsReader;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends CommonSeleniumMethods{

	@When("user enters valid username")
	public void user_enters_valid_username() {
		sendText(loginPage.txtUsername, ConfigsReader.getProperty("username"));
	}
	
	@When("user enters valid password")
	public void user_enters_valid_password() {
		sendText(loginPage.txtPassword, ConfigsReader.getProperty("password"));
	}

	@When("user enters valid username as {string}")
	public void user_enters_valid_username_as(String username) {
		sendText(loginPage.txtUsername, username);
	}
	@When("user leaves password empty as {string}")
	public void user_leaves_password_empty_as(String password) {
		sendText(loginPage.txtPassword, password);
	}

	@When("click on login button")
	public void click_on_login_button() {
	    loginPage.btnLogin.click();
	}
	
	@Then("I validate the user logged in")
	public void i_validate_the_user_logged_in() {
		waitForElementToBeVisible(productsPage.appLogo);
		assertTrue(productsPage.appLogo.isDisplayed());
	}

	@When("user leaves password empty")
	public void user_leaves_password_empty() {
		loginPage.txtPassword.clear();
	}

	@Then("I validate the {string} message is displayed")
	public void i_validate_the_message_is_displayed(String string) {
		waitForElementToBeVisible(loginPage.lblErrorMsg);
		assertTrue(loginPage.lblErrorMsg.isDisplayed());
	}




}
