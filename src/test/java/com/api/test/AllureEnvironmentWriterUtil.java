package com.api.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.ConfigManager;

public class AllureEnvironmentWriterUtil {
	public static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void environmentWriter() {
		String folderPath = "target/allure-results";

		File file = new File(folderPath);
		file.mkdirs();
		Properties properties = new Properties();
		properties.setProperty("Project Name", "Phoenix Test Automation Framwork");
		properties.setProperty("Env", ConfigManager.env);
		properties.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
		properties.setProperty("Operating System Name:", System.getProperty("os.name"));
		properties.setProperty("Operating System Version:", System.getProperty("os.version"));
		properties.setProperty("Java Version:", System.getProperty("java.version"));
		properties.setProperty("Java Home:", System.getProperty("java.home"));
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(folderPath + "/environment.properties");
			properties.store(fileWriter, "Setting Env deatils for Allure");
			LOGGER.info("created environment.properties file at {}",folderPath);
		} catch (IOException e) {
			LOGGER.error("unable to create environment.properties file at {} the issue is {}",folderPath,e);
			e.printStackTrace();
		}

	}

}
