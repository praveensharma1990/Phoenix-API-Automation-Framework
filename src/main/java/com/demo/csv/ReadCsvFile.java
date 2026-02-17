package com.demo.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile {

	public static void main(String[] args) throws IOException, CsvException {
		
		InputStream inputStream =Thread.currentThread().getContextClassLoader().getResourceAsStream("testdata/logincreds.csv");
		InputStreamReader is = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(is);
		List<String[]> dataList = csvReader.readAll();
		for (String[] value : dataList) {
			System.out.println(value[0]);
			System.out.println(value[1]);
		}

	}

}
