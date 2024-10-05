package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.testbase.BaseClass;

public class AddEmpModalPopUpFields {

	@FindBy(id = "first-name-box")
	public WebElement firstNameBox;

	@FindBy(id = "last-name-box")
	public WebElement lastNameBox;

	@FindBy(xpath="//input[@id='employeeId']")
	public WebElement empIDbox;
	
	@FindBy(xpath = "//select[@id='location']")
	public WebElement selectDropdown;

	@FindBy(xpath = "//input[@id='hasLoginDetails']")
	public WebElement toggleSwitch;

	@FindBy(xpath = "//input[@id='username']")
	public WebElement usernameBox;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement passwordBox;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	public WebElement confirmPasswordBox;

	@FindBy(id = "modal-save-button")
	public WebElement saveBtn;

	@FindBy(id = "firstName")
	public WebElement waitForFirstName;

	public AddEmpModalPopUpFields() {

		PageFactory.initElements(BaseClass.driver, this);
	}

}
