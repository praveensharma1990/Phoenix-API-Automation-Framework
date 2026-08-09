package com.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	
	private static Dotenv dotenv;
	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	
	
	private EnvUtil() {}
	
	static {
		LOGGER.info("loading the .env file...");
		dotenv = Dotenv.load();
	}
	
	public static String getValue(String varName) {
		LOGGER.info("reading the value of {} from .evn ",varName);
		return dotenv.get(varName);
	}

}
