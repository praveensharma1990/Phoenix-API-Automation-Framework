package com.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.utils.ConfigManager;
import com.api.utils.EnvUtil;
import com.api.utils.ValtDBConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataBaseManager {
	private static Logger lOGGER = LogManager.getLogger(DataBaseManager.class);
	private static final int MAXIUM_POOL_SIZE = Integer.parseInt(ConfigManager.getProperty("MAXIUM_POOL_SIZE"));
	private static final int MINIMUM_IDLE_COUNT = Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT"));
	private static final int MAX_LIFE_TIME_IN_MINS = Integer
			.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MINS"));
	private static final int CONNECTION_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SECS"));
	private static final int IDEAL_TIMEOUT_IN_SECS = Integer
			.parseInt(ConfigManager.getProperty("IDEAL_TIMEOUT_IN_SECS"));
	private static String HIKARI_CP_POOL_NAME = ConfigManager.getProperty("HIKARI_CP_POOL_NAME");

	private static HikariConfig hikariConfig;
	private static volatile HikariDataSource hikariDataSource;
	private static boolean isVaultUp = true;
	private static final String DB_URL = loadSecret("DB_URL");
	private static final String DB_USER_NAME = loadSecret("DB_USER_NAME");
	private static final String DB_PASSWORD = loadSecret("DB_PASSWORD");
	
	
	public static String loadSecret(String key) {
		String value = null;
		if(isVaultUp) {
		value = ValtDBConfig.getSecretes(key);
		if(value==null) {
			lOGGER.error("vault is down! or something went wrong!");
			isVaultUp = false;
		}
		
		else {
			lOGGER.info("reading value from vault for the key {}",key);
			return value;
		}
		}
		value = EnvUtil.getValue(key);
		lOGGER.info("reading value from .env file for the key {}",key);
				return value;
	}	

	private DataBaseManager() {
	}

	private static void intilizePool() throws SQLException {

		if (hikariDataSource == null)// first check all thread will enter.
		{  lOGGER.warn("Database connection is not available......creating HikaridataSource");
			synchronized (DataBaseManager.class) {
				if (hikariDataSource == null) {   // second check only one thread will enter
					hikariConfig = new HikariConfig();
					hikariConfig.setJdbcUrl(DB_URL);
					hikariConfig.setUsername(DB_USER_NAME);
					hikariConfig.setPassword(DB_PASSWORD);
					hikariConfig.setMaximumPoolSize(MAXIUM_POOL_SIZE);
					hikariConfig.setMinimumIdle(MINIMUM_IDLE_COUNT);
					hikariConfig.setMaxLifetime(MAX_LIFE_TIME_IN_MINS * 60 * 1000);
					hikariConfig.setConnectionTimeout(CONNECTION_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setIdleTimeout(IDEAL_TIMEOUT_IN_SECS * 1000);
					hikariConfig.setPoolName(HIKARI_CP_POOL_NAME);

					hikariDataSource = new HikariDataSource(hikariConfig);
					lOGGER.info("Hikari Datasource created");
				}
			}

		}

	}

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		if (hikariDataSource == null) {
			try {
				lOGGER.info("Initializing the database connection using Hikaricp");
				intilizePool();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (hikariDataSource.isClosed()) {
			lOGGER.error("Hikari dataSource is closed!!");
			throw new SQLException("hikari data source is closed");

		}
		try {
			connection = hikariDataSource.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}
}
