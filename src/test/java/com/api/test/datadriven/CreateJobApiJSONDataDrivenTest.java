package com.api.test.datadriven;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.request.model.CreateJobPayload;
import com.api.services.JobService;
@Listeners(com.listeners.APITestListener.class)
public class CreateJobApiJSONDataDrivenTest {
	private JobService jobService;
	
	@BeforeMethod(description = "Object creation for Job Service")
	public void setup() {
		jobService = new JobService();
	}
		
	@Test(description = "validate create job api response is correct for inwarranty", groups = { "dataDriven",
			"regression","JSON" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider="CreateApiJsonDataprovider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {
		jobService.createJob(UserRole.FD, createJobPayload)
		.then()
		.log()
		.all()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("schemaValidator/createJobApiSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "));

	}
}
