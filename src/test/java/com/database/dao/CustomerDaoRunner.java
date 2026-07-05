package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerProductDBModel;

public class CustomerDaoRunner {

	public static void main(String[] args) throws SQLException {
		CustomerProductDBModel customerProductDBModel;
		customerProductDBModel = CustomerProductDao.getCustomerProductInfoFromDB(341392);
		System.out.println(customerProductDBModel);

	}

}
