package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.JobHeadModel;

import io.qameta.allure.Step;

public class JobHeadDao {
	private static Logger lOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_HEAD_QUERY = """
				select * from tr_job_head tjh where tr_customer_id = ?;

			""";

	private JobHeadDao() {
	}
 
	@Step("Retriving the customer JobHead data from database")
	public static JobHeadModel getJobHeadData(int cutomerId) {
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		JobHeadModel jobHeadModel = null;
		try {
			lOGGER.info("Getting the connection from the Database Manager");
			connection = DataBaseManager.getConnection();
			preparedStatement = connection.prepareStatement(JOB_HEAD_QUERY);
			preparedStatement.setInt(1, cutomerId);
			lOGGER.info("Executing Query...{}",JOB_HEAD_QUERY);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				jobHeadModel = new JobHeadModel(resultSet.getInt("id"), resultSet.getString("job_number"),
						resultSet.getInt("tr_customer_id"), resultSet.getInt("tr_customer_product_id"),
						resultSet.getInt("mst_service_location_id"), resultSet.getInt("mst_platform_id"),
						resultSet.getInt("mst_warrenty_status_id"), resultSet.getInt("mst_oem_id"));

			}

		} catch (SQLException e) {
			lOGGER.error("Can not convert the resultSet to JobHeadModel bean",e);
		} return jobHeadModel;
	}

}
