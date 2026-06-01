package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.api.utils.ConfigManager;

public class DataBaseManager2 {
	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USER_NAME = ConfigManager.getProperty("DB_USER_NAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private static volatile Connection connection;

	private DataBaseManager2() {
	}

	public static void creatConnection() throws SQLException {

		if (connection == null)// first check all thread will enter.
		{
			synchronized (DataBaseManager2.class) {
				if (connection == null) { // only for first connection request.
					connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
				}

			}

		}
		System.out.println(connection);
	}

}
