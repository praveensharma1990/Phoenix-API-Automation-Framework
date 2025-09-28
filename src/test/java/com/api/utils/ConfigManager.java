package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private static Properties prop = new Properties();
	private static String PATH = "config/config.properties";
	private static String env;

	private ConfigManager() {
		// Prevent instantiation
	}

	static {
		env = System.getProperty("env", "qa").toLowerCase().trim();
		switch (env) {
		case "dev" -> PATH = "config/config.dev.properties";
		case "qa" -> PATH = "config/config.qa.properties";
		case "uat" -> PATH = "config/config.uat.properties";
		default -> PATH = "config/config.qa.properties";

		}
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH);

		if (inputStream == null) {
			throw new RuntimeException("Configuration file not found at path: " + PATH);
		}

		try {
			prop.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load properties file: " + PATH, e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				System.err.println("Failed to close input stream for: " + PATH);
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
