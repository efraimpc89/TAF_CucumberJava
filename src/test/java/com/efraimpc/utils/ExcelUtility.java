package com.efraimpc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {

	private static Workbook book;
	private static Sheet sheet;
	
	private static void openExcel(String filePath) {
		FileInputStream fileIS;
		
		try {
			fileIS = new FileInputStream(filePath);
			book = new XSSFWorkbook(fileIS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void loadSheet(String sheetName) {
		sheet = book.getSheet(sheetName);
	}
	
	private static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	
	private static int colsCount(int rowIndex) {
		return sheet.getRow(rowIndex).getLastCellNum();
	}
	
	private static String cellData(int rowIndex, int colIndex) {
		return sheet.getRow(rowIndex).getCell(colIndex).toString();
	}
	
	/**
	 * Returns the excel fila as a 2D object array
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static Object[][] excelIntoArray (String filePath, String sheetName) {
		openExcel(filePath);
		loadSheet(sheetName);
		
		int rows = rowCount();
		int cols = colsCount(0);
		
		Object[][] data = new Object[rows-1][cols];
		
		//iterate over rows
		for(int row = 1; row < rows; row++) {
			//iterate over columns
			for(int col = 0; col < cols; col++) {
				//store cell value in Object[][] data
				data[row - 1][col] = cellData(row,col);						
			}
		}
		return data;
		
	}
	
	/**
	 * Returns a list of maps from an excel file
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static List<Map<String, String>> excelIntoListOfMaps(String filePath, String sheetName){
		openExcel(filePath);
		loadSheet(sheetName);
		
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> excelMap;
		
		for(int row = 1; row < rowCount(); row++) {
			excelMap = new LinkedHashMap<>();
			for(int c = 0; c < colsCount(row); c++) {
				excelMap.put(cellData(0,c), cellData(row,c));
			}
			list.add(excelMap);
		}
		
		return list;
	}
	
}
