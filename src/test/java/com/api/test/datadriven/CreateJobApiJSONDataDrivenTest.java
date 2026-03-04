package com.api.test.datadriven;

import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.request.model.CreateJobPayload;

public class CreateJobApiJSONDataDrivenTest {
		
	@Test(description = "validate create job api response is correct for inwarranty", groups = { "dataDriven",
			"regression","JSON" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider="CreateApiJsonDataprovider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {
		given()
		.spec(requestSpecWithAuth(UserRole.FD, createJobPayload))
		.when().post("/job/create")
		.then()
		.log()
		.all()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("schemaValidator/createJobApiSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "));

	}
}
