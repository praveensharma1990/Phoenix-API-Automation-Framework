package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDemo {

	public static void main(String[] args) throws SQLException {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		config.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		config.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		HikariDataSource dSource = new HikariDataSource(config);
		Connection connection = dSource.getConnection();

		System.out.println("Connection establised " + connection);
		Statement statement =connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM SR_DEV.tr_customer;");
		while(resultSet.next()) {
			String first_Name = resultSet.getString("first_name");
			String last_Name = resultSet.getString("last_name");
			String mobile_number = resultSet.getString("mobile_number");
			System.out.printf("%-20s %-20s %-15s%n", first_Name, last_Name, mobile_number);
		}

	}

}
