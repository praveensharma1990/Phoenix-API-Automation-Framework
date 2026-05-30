package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://64.227.160.186:3306/SR_DEV";
        String username = "srdev_ro_automation";
        String password = "Srdev@123";

        try (Connection connection = DriverManager.getConnection(url, username, password))
        		{ System.out.println(connection);
        		Statement statement = connection.createStatement();
        		ResultSet resultSet=statement.executeQuery("SELECT first_name, last_name, mobile_number  FROM SR_DEV.tr_customer;");
        		while(resultSet.next()) {
        			String first_Name = resultSet.getString("first_name");
        			String last_Name = resultSet.getString("last_name");
        			String mobile_number = resultSet.getString("mobile_number");
        			System.out.printf("%-20s %-20s %-15s%n", first_Name, last_Name, mobile_number);
        		}
        	
                     } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}