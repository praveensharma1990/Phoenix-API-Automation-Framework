package com.api.test.datadriven;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problems;
import com.api.constant.ServiceLocation;
import com.api.constant.UserRole;
import com.api.constant.WarrentyStatus;
import com.api.constant.product;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problem;
import static com.api.utils.DateTimeProvider.getDateAndTimeDaysAgo;
import static com.api.utils.SpecUtil.*;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobApiDataDrivenTest {
		
	@Test(description = "validate create job api response is correct for inwarranty", groups = { "dataDriven",
			"regression" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider="createJobAPIDataProvider")
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
