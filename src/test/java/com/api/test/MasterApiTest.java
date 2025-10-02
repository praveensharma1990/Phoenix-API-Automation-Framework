package com.api.test;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterApiTest {
	
	@Test()
	public void masterApiTest() {
		given()
		.baseUri(ConfigManager.getProperty("BASE_URI"))
		.contentType("")
		.header("Authorization",AuthTokenProvider.getToken(UserRole.FD))
		.log().all()
		.when()
		.post("master")
		.then()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message",equalTo("Success"))
		.body("data", notNullValue())
		.body("data.mst_oem.size()",greaterThan(0))
		.body("data", allOf(
			    hasKey("mst_oem"),
			    hasKey("mst_model"),
			    hasKey("mst_action_status"),
			    hasKey("mst_warrenty_status"),
			    hasKey("mst_platform"),
			    hasKey("mst_product"),
			    hasKey("mst_role"),
			    hasKey("mst_service_location"),
			    hasKey("mst_problem"),
			    hasKey("map_fst_pincode")
			))
		.body("data.mst_oem.id",everyItem(not(nullValue())))
		.body("data.mst_oem.name",everyItem(not(nullValue())))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemaValidator/masterApiSchema.json"));
		
	}

}
