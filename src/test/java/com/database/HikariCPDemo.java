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
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setMaxLifetime(1800000);
		hikariConfig.setConnectionTimeout(100000);
		hikariConfig.setIdleTimeout(10000);
		hikariConfig.setPoolName("Phoenix Test Automation Framework Pool");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		Connection connection = dataSource.getConnection();

		System.out.println("Connection establised " + connection);
		Statement statement =connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM SR_DEV.tr_customer;");
		while(resultSet.next()) {
			String first_Name = resultSet.getString("first_name");
			String last_Name = resultSet.getString("last_name");
			String mobile_number = resultSet.getString("mobile_number");
			System.out.printf("%-20s %-20s %-15s%n", first_Name, last_Name, mobile_number);
		} dataSource.close();

	}

}
