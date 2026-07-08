package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.DataBaseManager;
import com.database.model.MapJobProblemModel;

public class MapJobProbelmDao {

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
			connection = DataBaseManager.getConnection();
			preparedStatement = connection.prepareStatement(JOB_PROBLEM_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				mapJobProblemModel = new MapJobProblemModel(resultSet.getInt("id"), resultSet.getInt("tr_job_head_id"),
						resultSet.getInt("mst_problem_id"), resultSet.getString("remark"));

			}

		} catch (SQLException e) {
			System.err.print(e.getMessage());
		}
		return mapJobProblemModel;
	}
}
