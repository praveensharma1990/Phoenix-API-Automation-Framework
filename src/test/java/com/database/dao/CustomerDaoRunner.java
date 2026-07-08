package com.database.dao;

import java.sql.SQLException;

import com.database.model.JobHeadModel;

public class CustomerDaoRunner {

	public static void main(String[] args) throws SQLException {
		JobHeadModel jobHeadModel;
		jobHeadModel = JobHeadDao.getJobHeadData(342422);
		System.out.println(jobHeadModel);

	}

}
