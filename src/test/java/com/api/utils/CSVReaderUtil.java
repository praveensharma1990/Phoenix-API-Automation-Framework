package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.qameta.allure.Step;

public class CSVReaderUtil {
	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtil.class);

    private CSVReaderUtil() {
    }

    @Step("Loading Test Data from csv File")
    public static <T>Iterator<T> loadCSV(String pathOfCSVFile,Class<T> bean) {
    	LOGGER.info("loading the csv file from the path {}",pathOfCSVFile);
    	InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCSVFile);
    	InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    	CSVReader csvReader = new CSVReader(inputStreamReader);
    	LOGGER.info("converting the csv to bean class {}",bean);
    	CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvReader)
    			.withType(bean)
    			.withIgnoreEmptyLine(true)
    			.build();
    	
    	List<T>list = csvToBean.parse();
    	return list.iterator();
    	

        
}
    
}
