package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.testbase.BaseClass;



public class DashboardPageElements {

	@FindBy(id="ohrm-small-logo")
	public WebElement logo;
	
	@FindBy(id="account-name")
	public WebElement accountName;
	
	@FindBy(xpath="//span[text()='PIM']")
	public WebElement PIM;
	
	@FindBy(xpath="//span[text()='Add Employee']")
	public WebElement addEmp;
	
//	@FindBy(linkText="Add Employee")
//	public WebElement addEmployeeLink;
	
	@FindBy(xpath="//h5[text()='Add Employee']")
	public WebElement addEmpModalPopUp;
	
	
	
	
	public DashboardPageElements() {
		
		PageFactory.initElements(BaseClass.driver, this);
		
	}
}
