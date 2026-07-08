package com.api.test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.UserRole;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.dao.JobHeadDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.database.model.JobHeadModel;

import static com.api.utils.SpecUtil.*;

import static io.restassured.RestAssured.*;

public class CreateJobApiWithFakerTest {
	private CreateJobPayload payload;

	@BeforeMethod(description = "creating request Payload for Create job api")
	public void setup() {

		payload = FakerDataGenerator.generateFakeCreateJobData();

	}

	@Test(description = "validate create job api response is correct for inwarranty", groups = { "smoke",
			"regression" })
	public void createJobApiWithFakerDataTest() {
		int customerID = given().spec(requestSpecWithAuth(UserRole.FD, payload)).when().post("/job/create").then().log()
				.all().statusCode(200).body(matchesJsonSchemaInClasspath("schemaValidator/createJobApiSchema.json"))
				.body("message", equalTo("Job created successfully. ")).body("data.job_number", startsWith("JOB_"))
				.extract().body().jsonPath().getInt("data.tr_customer_id");
		Customer expectedCustomerData = payload.customer();
		CustomerDBModel actualCustomerDataInDB = CustomerDao.getCustomerInfo(customerID);
		Assert.assertEquals(expectedCustomerData.first_name(), actualCustomerDataInDB.getFirst_name());
		Assert.assertEquals(expectedCustomerData.last_name(), actualCustomerDataInDB.getLast_name());
		Assert.assertEquals(expectedCustomerData.mobile_number(), actualCustomerDataInDB.getMobile_number());
		Assert.assertEquals(expectedCustomerData.mobile_number_alt(), actualCustomerDataInDB.getMobile_number_alt());
		Assert.assertEquals(expectedCustomerData.email_id(), actualCustomerDataInDB.getEmail_id());
		Assert.assertEquals(expectedCustomerData.email_id_alt(), actualCustomerDataInDB.getEmail_id_alt());

		CustomerAddressDBModel actualCustomerAddress = CustomerAddressDao
				.getCustomerAddressData(actualCustomerDataInDB.getTr_customer_address_id());
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

		JobHeadModel jobHeadAcctualData = JobHeadDao.getJobHeadData(customerID);
		Assert.assertEquals(payload.mst_oem_id(), jobHeadAcctualData.getMst_oem_id());
		Assert.assertEquals(payload.mst_platform_id(), jobHeadAcctualData.getMst_oem_id());
		Assert.assertEquals(payload.mst_service_location_id(), jobHeadAcctualData.getMst_service_location_id());
		Assert.assertEquals(payload.mst_warrenty_status_id(), jobHeadAcctualData.getMst_warrenty_status_id());

	}
}
