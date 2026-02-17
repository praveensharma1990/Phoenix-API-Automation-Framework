package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCsvFile2MapPojo {

	public static void main(String[] args) throws IOException, CsvException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("testdata/logincreds.csv");
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		CSVReader csvReader = new CSVReader(inputStreamReader);
		List<UserPojo> userCred = new CsvToBeanBuilder(csvReader).withType(UserPojo.class).withIgnoreEmptyLine(true)
				.build().parse();
		if (!userCred.isEmpty()) {
			System.out.println(userCred);
		}

	}
}
