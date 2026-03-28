package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtil {

	public static void main(String[] args) {

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testData/phoenixTestData.xlsx");
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkbook.getSheet("LoginTestData");
		XSSFRow row;
		XSSFCell cell;
		int lastRowIndex = mySheet.getLastRowNum();
		XSSFRow rowHeader = mySheet.getRow(0);
		int lastColumnIndex = rowHeader.getLastCellNum() - 1;

		for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {

			for (int columnIndex = 0; columnIndex <= lastColumnIndex; columnIndex++) {
				row = mySheet.getRow(rowIndex);
				cell = row.getCell(columnIndex);
				System.out.printf("%-15s", cell.toString());
			}
			System.out.println("");
		}

	}
}
