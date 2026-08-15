package com.api.test;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.response.model.Search;
import com.api.services.JobService;
@Listeners(com.listeners.APITestListener.class)
public class JobSearchTest {
	
	private JobService jobService;
	private Search seachPayload;
	private static final String JOB_NUMBER = "JOB_365290";
	
	@BeforeMethod(description = "Instantiating the Job Service")
	public void setup() {
		jobService = new JobService();
		seachPayload = new Search(JOB_NUMBER);
		
	}
	@Test(description = "validte the job is being fetched correctly",groups = {"smoke","api"})
	public void searchApiTest() {
		jobService.search(UserRole.FD, seachPayload)
		.then()
		.statusCode(200)
		.body("message", Matchers.equalToIgnoringCase("success"));
	}

}
