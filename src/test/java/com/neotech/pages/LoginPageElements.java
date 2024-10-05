package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.testbase.BaseClass;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class LoginPageElements extends CommonMethods{

	@FindBy(id = "txtUsername")
	public WebElement usernameBox;

	@FindBy(id = "txtPassword")
	public WebElement passwordBox;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement loginBtn;

	@FindBy(id = "txtPassword-error")
	public WebElement passwordEmptyError;

	@FindBy(xpath = "//div[@class='toast-message']")
	public WebElement wrongPasswordError;

	public LoginPageElements() {

		PageFactory.initElements(BaseClass.driver, this);
	}

	public void adminLogin() {

		sendText(usernameBox, ConfigsReader.getProperty("username"));
		sendText(passwordBox, ConfigsReader.getProperty("password"));
		click(loginBtn);
		
	}

}
