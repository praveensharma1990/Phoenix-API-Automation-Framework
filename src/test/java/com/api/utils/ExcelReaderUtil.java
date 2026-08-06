package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil {
	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);
	private ExcelReaderUtil() {
	}

	public static <T> Iterator<T> excelReader(String excelFilePath ,String sheetName,Class<T> class1) {
		LOGGER.info("reading the excel file from {} sheet name {}",excelFilePath,sheetName);
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelFilePath);
		XSSFWorkbook myWorkbook = null;
		try {
			myWorkbook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("can not read the excel file {}",excelFilePath,e);
			e.printStackTrace();
		}
		XSSFSheet mySheet = myWorkbook.getSheet(sheetName);
	List<T>dataList = Poiji.fromExcel(mySheet, class1);
	LOGGER.info("converting the excel sheet {} to pojo class type {}",sheetName,class1);
	return dataList.iterator();		

	}
}
