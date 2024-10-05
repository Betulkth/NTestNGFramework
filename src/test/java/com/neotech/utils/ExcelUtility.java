package com.neotech.utils;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	// 1
	private static Workbook book;
	private static Sheet sheet;
	
	
	/** This method will initialize a Workbook object given the filePath.
	 * 
	 * @param filePath
	 */
	private static void openExcel(String filePath) {
		
		try {
			
			FileInputStream fis = new FileInputStream(filePath);
			book = new XSSFWorkbook(fis);
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	
	// 2
	/** This method will load a sheet given the sheetName.
	 * 
	 * @param sheetName
	 */
	private static void loadSheet(String sheetName) {
		
		sheet = book.getSheet(sheetName);
	}
	
	
	
	// 3
	/** This method will return the row count of the current sheet.
	 * 
	 * @return
	 */
	private static int rowCount() {
		
		return sheet.getPhysicalNumberOfRows();
	}
	
	
	// 4 
	/** This method will return the column count for a given row index in the current sheet,
	 * 
	 * @param rowIndex
	 * @return
	 */
	private static int colCount(int rowIndex) {
		
		return sheet.getRow(rowIndex).getLastCellNum();
	}
	
	/** This method will return the cell data as a String given the row and column number.
	 *  
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private static String cellData(int rowIndex, int colIndex) {
		
		return sheet.getRow(rowIndex).getCell(colIndex).toString();
	}
	
	
	
	public static Object[][] excelIntoArray(String filePath, String sheetName){
		
		// open the file : filePath
		openExcel(filePath);
		
		// load the sheet : sheetName
		loadSheet(sheetName);
		
		// define the number of rows
		int rowNumber = rowCount();
		
		// define the number of columns
		int colNumber = colCount(0);
		
		
		// nested loop to go through each cell and get data from it
		
		Object[][] data = new Object[rowNumber - 1][colNumber];
		
		for(int row = 1; row < rowNumber; row++) {
			// for each row (ignoring the first row ( row with index 0 -- its the header )
			
			for(int col = 0; col < colNumber; col++) {
				// for each column on a row
				
				data [row - 1][col] = cellData(row,col); 
				
			}
		}
		
		return data;
		
		
		
	}
}
