package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtil {

    private CSVReaderUtil() {
    }

    public static Iterator<UserBean> loadCSV(String pathOfCSVFile) {

        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(pathOfCSVFile);

        if (inputStream == null) {
            throw new RuntimeException("CSV file not found at path: " + pathOfCSVFile);
        }

        try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             CSVReader csvReader = new CSVReader(inputStreamReader)) {

            List<UserBean> userCred = new CsvToBeanBuilder<UserBean>(csvReader)
                    .withType(UserBean.class)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();

            return userCred.iterator();

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse CSV file: " + pathOfCSVFile, e);
        }
    }
}
