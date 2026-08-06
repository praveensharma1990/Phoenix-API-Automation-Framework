package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {
	
	private static Logger lOGGER = LogManager.getLogger(CustomerDao.class);
	private static final String CUSTUMER_DETAILS_QUERY = """
			select * from tr_customer where id =?
			""";
private CustomerDao() {}
	public static CustomerDBModel getCustomerInfo(int customerID) {
		CustomerDBModel customerDBModel = null;
		try {
			lOGGER.info("Getting the connection from the Database Manager");
			Connection connection = DataBaseManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CUSTUMER_DETAILS_QUERY);
			preparedStatement.setInt(1, customerID);
			lOGGER.info("executing the Query..{}",CUSTUMER_DETAILS_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				customerDBModel = new CustomerDBModel(resultSet.getInt("id"),resultSet.getString("first_name"),
						resultSet.getString("last_name"), resultSet.getString("mobile_number"),
						resultSet.getString("mobile_number_alt"), resultSet.getString("email_id"),
						resultSet.getString("email_id_alt"),resultSet.getInt("tr_customer_address_id"));
			}
		} catch (SQLException e) {
			lOGGER.error("can not convert the resultSet to CustomerDBModel dao");
			System.err.print(e.getMessage());

		}

		return customerDBModel;
	}

}
