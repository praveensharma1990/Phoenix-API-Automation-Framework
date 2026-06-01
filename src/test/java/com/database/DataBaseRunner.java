package com.database;

import java.sql.SQLException;

public class DataBaseRunner {

	public static void main(String[] args) throws SQLException {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			DataBaseManager2.creatConnection();

		}
		long endTime = System.currentTimeMillis();

		System.out.println("time taken " + (endTime - startTime) + "ml");
		System.out.println("Start Time is " + startTime);
		System.out.println("End Time is " + endTime);

	}

}
