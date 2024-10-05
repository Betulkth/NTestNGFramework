package com.neotech.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;


public class LoginTest extends CommonMethods {

	@Test(groups = {"smoke", "regression"})
	public void validLogin() {

		LoginPageElements login = new LoginPageElements();
		DashboardPageElements dashboard = new DashboardPageElements();

	
		// send username
		sendText(login.usernameBox, ConfigsReader.getProperty("username"));

		// send password
		sendText(login.passwordBox, ConfigsReader.getProperty("password"));

		// click the login button
		click(login.loginBtn);

		// verify that we are in
		String expected = "Jacqueline White";
		String actual = dashboard.accountName.getText();

		// assertion to verify the expected and actual are equal
		Assert.assertEquals(actual, expected, "The Account name does NOT match! ");

	}

	@Test(groups = "smoke")
	public void emptyPasswordLogin() {

		LoginPageElements login = new LoginPageElements();

		test.info("Sending username...");
		// send username
		sendText(login.usernameBox, ConfigsReader.getProperty("username"));

		test.info("Leaving the password empty...");
		// leave the password empty
		sendText(login.passwordBox, "");

		test.info("Logging in...");
		// click the login button
		click(login.loginBtn);
		
		// Assert that proper message is shown
		String expected = "Password cannot be empty";
		String actual = login.passwordEmptyError.getText();
		
		Assert.assertEquals(actual, expected, "Error messages do NOT shown!");
		
		
		
	}
	
	@Test(groups = "regression")
	public void invalidPasswordLogin() {

		LoginPageElements login = new LoginPageElements();

		test.info("Sending username...");
		// send username
		sendText(login.usernameBox, ConfigsReader.getProperty("username"));

		test.info("Sending wrong password...");
		// send wrong password
		sendText(login.passwordBox, "wrongPassword");

		test.info("Logging in...");
		// click the login button
		click(login.loginBtn);
		
		// assert the invalid credentials message is shown
		String expected = "Invalid Credentials";
		String actual = login.wrongPasswordError.getText();
		
		test.info("The expected message: " + expected);
		test.info("The actual message: " + actual);
		
		
		
		
		Assert.assertEquals(actual, expected, "Error mesaage do NOT match!");
		
	}
	
	
	
	
	
	
	

}
