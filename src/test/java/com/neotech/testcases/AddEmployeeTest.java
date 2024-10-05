package com.neotech.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.pages.AddEmpModalPopUpFields;
import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.pages.PersonalDetailsPageElements;
import com.neotech.utils.CommonMethods;
import com.neotech.utils.ExcelUtility;

//Create AddEmployeeTest.java similar to Homework1.java under com/neotech/lesson04 package in TestNG project.
//Follow Page Object Model design pattern, don't find any elements inside the test method!
//
//Create a Test Method named addEmployee with the following steps:
//Login into the application
//Navigate to PIM and Add Employee
//Provide First Name and Last Name
//Create Login Details
//Provide User Name and Password
//Save the Employee
//Verify Employee has been added successfully
//
//This test method should belong to addEmp group.
//By using @DataProvider, add 3 different employees using Excel.xlsx file.
//
//Create an xml file named addEmp.xml similar to smoke.xml file and execute the xml file.

public class AddEmployeeTest extends CommonMethods {

	@Test(dataProvider = "excelData", groups = {"addEmp", "regression"})
	public void AddEmployee(String firstName, String lastName, String location, String userName, String password,
			String confirmPassword) {

//		LoginPageElements login = new LoginPageElements();
//		DashboardPageElements dash = new DashboardPageElements();
//		AddEmpModalPopUpFields fields = new AddEmpModalPopUpFields();
//		PersonalDetailsPageElements personalD = new PersonalDetailsPageElements();	

		test.info("Testing for employee: " + firstName + " " + lastName);
		// Login into the application
		test.info("Login into the aplication");
		login.adminLogin();

		// Navigate to PIM and Add Employee
        test.info("Navigating to the dashboard.");
		click(dash.PIM);
		click(dash.addEmp);

		//wait for the text box to show
		waitForVisibility(By.xpath("addEmpModalPopUp"));

		// Create Login Details
		// Provide First Name and Last Name
     	test.info("Adding an employee");
		sendText(fields.firstNameBox, firstName);
		sendText(fields.lastNameBox, lastName);
		
		String expectedEmpID = dash.addEmpModalPopUp.getAttribute("value");
		
		selectDropdown(fields.selectDropdown, location);
		jsClick(fields.toggleSwitch);
		
		// Provide User Name and Password
		sendText(fields.usernameBox, userName);
		sendText(fields.passwordBox, password);
		sendText(fields.confirmPasswordBox, confirmPassword);

		// Save the Employee
		test.info("Saving the employee");
		click(fields.firstNameBox);
		wait(1);
		jsClick(fields.saveBtn);

		//wait for the personal details to show
		waitForVisibility(By.id("firstName"));

		// Verify Employee has been added successfully
		test.info("Validating the employee...");
		String actualEmpID = personalD.employeeId.getAttribute("value");
		
        Assert.assertEquals(actualEmpID, expectedEmpID, "Employee could NOT saved!!");

		
		
	}
	
	//By using @DataProvider, add 3 different employees using Excel.xlsx file.
	
	@DataProvider(name="excelData")
	public Object[][] getExcelData(){
		
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/EmployeeInfo.xlsx";
		
		
		return ExcelUtility.excelIntoArray(path, "Employees");
	}

}
