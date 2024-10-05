package com.neotech.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.neotech.testbase.BaseClass;

public class PersonalDetailsPageElements {

	
	@FindBy(id="firstName")
	public WebElement firstName;
	
	@FindBy(id="employeeId")
	public WebElement employeeId;
	
	
	public PersonalDetailsPageElements()
	{
		PageFactory.initElements(BaseClass.driver, this);
	}
}
