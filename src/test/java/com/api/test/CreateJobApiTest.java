package com.api.test;

import io.restassured.module.jsv.JsonSchemaValidator;

import org.hamcrest.Matchers;
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
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {
   Customer customer = new Customer("Ram", "Sharma", "9161759333", "", "psagra13@gmail.com", "psagra12@gmail.com");
   CustomerAddress customerAddress = new CustomerAddress("B 233", "Ajanja", "Vashundra", "noida", "near mother dairy", "201301", "Uttar Pradesh", "India");
   CustomerProduct customerProduct=new CustomerProduct("23456781801708", "23456799101808", "23456689101108",getDateAndTimeDaysAgo(8), getDateAndTimeDaysAgo(8), product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getModelCode());
   Problem problems=new Problem(Problems.POOR_BATTERY_LIFE.getCode(), "Battery Backup is only 30 minuts");
   List<Problem>problemsList=new ArrayList<>();
   problemsList.add(problems);
   
   CreateJobPayload payload=new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), WarrentyStatus.IN_WARRANTY.getCode(), OEM.Google.getCode(), customer, customerAddress, customerProduct, problemsList);

		given()
		.spec(SpecUtil.requestSpecWithAuth(UserRole.FD, payload))
		.when()
		.post("/job/create")
		.then()
		.log()
		.all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/createJobApiSchema.json"))
		.body("message",Matchers.equalTo("Job created successfully. "));	
	}
}
