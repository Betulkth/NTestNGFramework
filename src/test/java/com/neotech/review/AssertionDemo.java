package com.neotech.review;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionDemo {

	@Test
	public void test1() {

		String expected = "Betul";
		String actual = "Max";

		Assert.assertEquals(actual, expected);

		// Will this line be executed? NO
		// If the assertion fails, this line will NOT be executed
		System.out.println("End of the code!");

	}

	@Test
	public void test2() {

		int expectedAge = 27;
		int actualAge = 30;

		Assert.assertEquals(actualAge, expectedAge, "The age is NOT correct!");

	}

	@Test
	public void test3() {

		// When I log in I am expecting this list in the menu

		List<String> expectedList = new ArrayList<>();

		expectedList.add("Admin");
		expectedList.add("PIM");
		expectedList.add("My Info");
		expectedList.add("Discipline");

		// When I log in I am reading the list in the menu
		// Let us suppose that I used Selenium to locate the list below

		List<String> actualList = new ArrayList<>();
		actualList.add("Admin");
		actualList.add("PIM");
		actualList.add("My Info");
		actualList.add("Discipline");

		Assert.assertEquals(actualList, expectedList);

	}

	@Test
	public void test4() {

		boolean result = true;

		Assert.assertTrue(result);

	}

	@Test
	public void test5() {

		// With softAssert, we can run multiple assertions in the @Test

		SoftAssert softAssert = new SoftAssert();

		String expected = "break";
		String actual = "task";

		softAssert.assertEquals(actual, expected);

		// Will this line be executed? YES
		System.out.println("End of the code");

		boolean result = true;
		softAssert.assertTrue(result);

		int age1 = 26;
		int age2 = 41;

		softAssert.assertEquals(age1, age2);

		// This is the real checkpoint.

		softAssert.assertAll();

	}

}
