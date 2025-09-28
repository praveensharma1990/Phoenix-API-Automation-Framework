package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager1 {
	private static Properties prop = new Properties();

	private ConfigManager1() {

	}

	static {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties";
		File configFile = new File(filePath);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty(key);

	}
}
