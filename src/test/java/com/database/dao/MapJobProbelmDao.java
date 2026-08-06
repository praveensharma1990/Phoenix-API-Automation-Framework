package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DataBaseManager;
import com.database.model.MapJobProblemModel;

public class MapJobProbelmDao {
	private static Logger lOGGER = LogManager.getLogger(JobHeadDao.class);
	private static final String JOB_PROBLEM_QUERY = """
			select * from map_job_problem mjp where tr_job_head_id = ?;
			""";

	private MapJobProbelmDao() {
	}

	public static MapJobProblemModel getProblemDetails(int tr_job_head_id) {
		Connection connection;
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		MapJobProblemModel mapJobProblemModel = null;
		try {
			lOGGER.info("Getting the connection from the Database Manager");
			connection = DataBaseManager.getConnection();
			preparedStatement = connection.prepareStatement(JOB_PROBLEM_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			lOGGER.info("Executing Query...{}",JOB_PROBLEM_QUERY);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mapJobProblemModel = new MapJobProblemModel(resultSet.getInt("id"), resultSet.getInt("tr_job_head_id"),
						resultSet.getInt("mst_problem_id"), resultSet.getString("remark"));

			}

		} catch (SQLException e) {
			lOGGER.error("Can not convert the resultSet to mapJobProblemModel bean",e);
		}
		return mapJobProblemModel;
	}
}
