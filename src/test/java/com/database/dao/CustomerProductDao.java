package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {
	private static Logger lOGGER = LogManager.getLogger(CustomerProductDao.class);
	private static final String PRODUCT_QUERY = """
			select * from tr_customer_product where id =?
			""";

	private CustomerProductDao() {
	}

	public static CustomerProductDBModel getCustomerProductInfoFromDB(int trCustomerProductId) {

		CustomerProductDBModel customerProduct = null;
		lOGGER.info("Getting the connection from the Database Manager");
		try (Connection connection = DataBaseManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_QUERY)) {

			preparedStatement.setInt(1, trCustomerProductId);
			lOGGER.info("Executing Query...{}",PRODUCT_QUERY);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					customerProduct = new CustomerProductDBModel(resultSet.getInt("id"),
							resultSet.getInt("tr_customer_id"), resultSet.getInt("mst_model_id"),
							resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
							resultSet.getString("imei1"), resultSet.getString("serial_number"));
				}
			}

		} catch (SQLException e) {
			lOGGER.error("Can not Convert the ResultSet to CustomerProductDBModel",e);
			e.printStackTrace();
		}

		return customerProduct;
	}
}
