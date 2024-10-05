package com.neotech.review;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.utils.ExcelUtility;

public class DataProviderDemo {

	
	
	@Test(dataProvider = "ExcelData")
	public void printInformation(String name, String lastName, String age) {
		
		System.out.println("Full Name and AGE -> " + name + " " + lastName + " " + age);
		
	}
	
	
	@DataProvider(name= "hardCoded")
	public Object[][] createData(){
		
		Object[][] data = {{"Betul", "Karatas", "27"}, 
				{"Max", "Taylor", "29"}, 
				{"Asli", "Albayrak", "28"}};
		
		return data;
	}
	
	
	
	@DataProvider(name = "ExcelData")
	public Object[][] getExcelData(){
		
		String path = System.getProperty("user.dir") + "/src/test/resources/testdata/DataProviderDemo.xlsx";
		
		String sheet = "Data";
		
		return ExcelUtility.excelIntoArray(path, sheet);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
