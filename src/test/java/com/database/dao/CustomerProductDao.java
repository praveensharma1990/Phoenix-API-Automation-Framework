package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.CustomerProductDBModel;

public class CustomerProductDao {

	private static final String PRODUCT_QUERY = """
			select * from tr_customer_product where id =?
			""";

	private CustomerProductDao() {
	}

	public static CustomerProductDBModel getCustomerProductInfoFromDB(int trCustomerProductId) {

		CustomerProductDBModel customerProduct = null;

		try (Connection connection = DataBaseManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_QUERY)) {

			preparedStatement.setInt(1, trCustomerProductId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					customerProduct = new CustomerProductDBModel(resultSet.getInt("id"),
							resultSet.getInt("tr_customer_id"), resultSet.getInt("mst_model_id"),
							resultSet.getString("dop"), resultSet.getString("popurl"), resultSet.getString("imei2"),
							resultSet.getString("imei1"), resultSet.getString("serial_number"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customerProduct;
	}
}
