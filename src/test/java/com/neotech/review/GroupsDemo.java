package com.neotech.review;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GroupsDemo {

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {
		System.out.println("Creating a report...");
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() {
		System.out.println("Saving a report...");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		System.out.println("Opening the browser...");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("Closing the browser...");
	}

	@Test(priority = 3, groups = { "group1", "group2" })
	public void test1() {

		System.out.println("Addding an Employee in GroupsDemo!");

		Assert.assertTrue(false);
	}

	@Test(priority = 1, groups = { "group1" }, dependsOnMethods = "test1")
	public void test2() {

		System.out.println("Deleting an Employee in GroupsDemo!");
	}
}
