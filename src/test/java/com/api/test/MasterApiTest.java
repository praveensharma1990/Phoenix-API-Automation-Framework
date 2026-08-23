package com.api.test;

import static com.api.utils.SpecUtil.responseSpecificationText;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.services.MasterService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.listeners.APITestListener.class)
@Epic("Job Management")
@Feature("Master API")
public class MasterApiTest {
	private MasterService masterService;
	
	@BeforeMethod(description = "Instanciate Master Service")
	public void setup() {
		masterService = new MasterService();
	}
	
	@Test(description="validate response is shown correctly",groups= {"smoke","regression"})
	@Story("Master Api should bring OEM details")
	@Description("validate Master API response is shown correctly")
	@Severity(SeverityLevel.BLOCKER)
	public void masterApiTest() {
		masterService.master(UserRole.FD)
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
		.body(matchesJsonSchemaInClasspath("schemaValidator/masterApiSchema.json"));
		
	} 
	@Test(description="Validate Negative test for Invalid Token",groups= {"negative","smoke","regression"})
	public void invalidTokenMasterApi() {
		masterService.masterWithoutAuth()
		.then()
		.spec(responseSpecificationText(401))
		.log().all();
	}

}
