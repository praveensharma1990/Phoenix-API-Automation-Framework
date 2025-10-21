package com.api.test;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problem;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {
   Customer customer = new Customer("Ram", "Sharma", "9161759333", "", "psagra13@gmail.com", "psagra12@gmail.com");
   CustomerAddress customerAddress = new CustomerAddress("B 233", "Ajanja", "Vashundra", "noida", "near mother dairy", "201301", "Uttar Pradesh", "India");
   CustomerProduct customerProduct=new CustomerProduct("23456781101700", "23456799171800", "23456789101107", "2025-09-30T18:30:00.000Z", "2025-09-30T18:30:00.000Z", 1, 1);
   Problem problems=new Problem(3, "NA");
   Problem problemsArray[]=new Problem[1];
   problemsArray[0]=problems;
   
   CreateJobPayload payload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);

		given()
		.spec(SpecUtil.requestSpecWithAuth(UserRole.FD, payload))
		.post("/job/create")
		.then().log().all()
		.statusCode(200);		
	}
}
