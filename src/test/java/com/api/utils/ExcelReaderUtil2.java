package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {
	private ExcelReaderUtil2() {
	}

	public static <T> Iterator<T> excelReader(String excelFilePath ,String sheetName,Class<T> class1) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelFilePath);
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkbook.getSheet(sheetName);
	List<T>dataList = Poiji.fromExcel(mySheet, class1);
	return dataList.iterator();
		
		 
		

	}
}
