package com.api.test;

import static com.api.utils.DateTimeProvider.getDateAndTimeDaysAgo;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
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
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.CustomerProductDao;
import com.database.dao.JobHeadDao;
import com.database.dao.MapJobProbelmDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.CustomerProductDBModel;
import com.database.model.JobHeadModel;
import com.database.model.MapJobProblemModel;

import io.restassured.response.Response;

public class CreateJobApiDBValidationTest {
	private CreateJobPayload payload;
	private Customer customer;

	@BeforeMethod(description = "creating request Payload for Create job api")
	public void setup() {
		customer = new Customer("Ram", "Sharma", "9161759333", "", "psagra13@gmail.com", "psagra12@gmail.com");
		CustomerAddress customerAddress = new CustomerAddress("B 233", "Ajanja", "Vashundra", "noida",
				"near mother dairy", "201301", "Uttar Pradesh", "India");
		CustomerProduct customerProduct = new CustomerProduct("111156781801111", "111156781801111", "111156781801111",
				getDateAndTimeDaysAgo(8), getDateAndTimeDaysAgo(8), product.NEXUS_2.getCode(),
				Model.NEXUS_2_BLUE.getModelCode());
		Problem problems = new Problem(Problems.POOR_BATTERY_LIFE.getCode(), "Battery Backup is only 30 minuts");
		List<Problem> problemsList = new ArrayList<>();
		problemsList.add(problems);

		payload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(),
				WarrentyStatus.IN_WARRANTY.getCode(), OEM.Google.getCode(), customer, customerAddress, customerProduct,
				problemsList);

	}

	@Test(description = "validate create job api response is correct for inwarranty", groups = { "smoke",
			"regression" })
	public void createJobApiTest() {
		Response response = given().spec(requestSpecWithAuth(UserRole.FD, payload)).when().post("/job/create").then()
				.log().all().statusCode(200)
				.body(matchesJsonSchemaInClasspath("schemaValidator/createJobApiSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. ")).extract().response();

		int customerId = response.then().extract().body().jsonPath().getInt("data.tr_customer_id");
		System.out.println(customerId);
		CustomerDBModel customerDataFromDb = CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(customer.first_name(), customerDataFromDb.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDataFromDb.getLast_name());
		Assert.assertEquals(customer.mobile_number(), customerDataFromDb.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDataFromDb.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(), customerDataFromDb.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDataFromDb.getEmail_id_alt());

		CustomerAddressDBModel actualCustomerAddress = CustomerAddressDao
				.getCustomerAddressData(customerDataFromDb.getTr_customer_address_id());
		CustomerAddress expectedCustomerAddress = payload.customer_address();
		Assert.assertEquals(expectedCustomerAddress.flat_number(), actualCustomerAddress.getFlat_number());
		Assert.assertEquals(expectedCustomerAddress.apartment_name(), actualCustomerAddress.getApartment_name());
		Assert.assertEquals(expectedCustomerAddress.area(), actualCustomerAddress.getArea());
		Assert.assertEquals(expectedCustomerAddress.street_name(), actualCustomerAddress.getStreet_name());
		Assert.assertEquals(expectedCustomerAddress.landmark(), actualCustomerAddress.getLandmark());
		Assert.assertEquals(expectedCustomerAddress.pincode(), actualCustomerAddress.getPincode());
		Assert.assertEquals(expectedCustomerAddress.pincode(), actualCustomerAddress.getPincode());
		Assert.assertEquals(expectedCustomerAddress.state(), actualCustomerAddress.getState());
		Assert.assertEquals(expectedCustomerAddress.country(), actualCustomerAddress.getCountry());

		int productId = response.then().extract().body().jsonPath().getInt("data.tr_customer_product_id");
		CustomerProductDBModel customerProductActual = CustomerProductDao.getCustomerProductInfoFromDB(productId);
		CustomerProduct customerProductExpected = payload.customer_product();
		Assert.assertEquals(customerProductExpected.mst_model_id(), customerProductActual.getMst_model_id());
		Assert.assertEquals(customerProductExpected.imei1(), customerProductActual.getImei1());
		Assert.assertEquals(customerProductExpected.imei2(), customerProductActual.getImei2());
		Assert.assertEquals(customerProductExpected.dop().substring(0, 10),
				customerProductActual.getDop().substring(0, 10));
		Assert.assertEquals(customerProductExpected.popurl(), customerProductActual.getPopurl());
		Assert.assertEquals(customerProductExpected.serial_number(), customerProductActual.getSerial_number());

		int job_head_id = response.then().extract().body().jsonPath().getInt("data.id");
		MapJobProblemModel problemDataFromDB = MapJobProbelmDao.getProblemDetails(job_head_id);
		Assert.assertEquals(payload.problems().getFirst().id(), problemDataFromDB.getMst_problem_id());
		Assert.assertEquals(payload.problems().getFirst().remark(), problemDataFromDB.getRemark());

		JobHeadModel jobHeadAcctualData = JobHeadDao.getJobHeadData(customerId);
		Assert.assertEquals(payload.mst_oem_id(), jobHeadAcctualData.getMst_oem_id());
		Assert.assertEquals(payload.mst_platform_id(), jobHeadAcctualData.getMst_platform_id());
		Assert.assertEquals(payload.mst_service_location_id(), jobHeadAcctualData.getMst_service_location_id());

	}
}
