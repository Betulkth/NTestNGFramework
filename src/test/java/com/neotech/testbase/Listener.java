package com.neotech.testbase;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.neotech.utils.CommonMethods;

public class Listener implements ITestListener {

	public void onStart(ITestContext context) {

		System.out.println("Functionality test started!");
	}

	public void onFinish(ITestContext contex) {

		System.out.println("Functionality test finished!");
	}

	// onStart - onFinish is about Test suite in the xml file
	
	
	
	
	
	
	// These 3 methods about the test method in the class
	public void onTestStart(ITestResult result) {
		// prints to the console
		System.out.println(result.getName() + " test is starting!");

		//create a test report just before the @Test starts
				BaseClass.test = BaseClass.report.createTest(result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		// print on the console
		System.out.println(result.getName() + " test just passed!");

		//print the test pass on the report
				BaseClass.test.pass("Test Passed: " + result.getName());
				
				//take a screenshot and add it to the report
				String successImage = CommonMethods.takeScreenshot("passed/"+result.getName());
				BaseClass.test.addScreenCaptureFromPath(successImage);
			}
		
	

	public void onTestFailure(ITestResult result) {
		// printing on console
		System.out.println(result.getName() + " test just failed!");

		//print the test failure on the report
				BaseClass.test.fail("Test Failed: " + result.getName());
				
				//take a screenshot and add it to the report
				String failImage = CommonMethods.takeScreenshot("failed/"+result.getName());
				BaseClass.test.addScreenCaptureFromPath(failImage);
		

		

	}

}
