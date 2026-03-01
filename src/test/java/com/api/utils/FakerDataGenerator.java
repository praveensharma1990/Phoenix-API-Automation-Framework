package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problem;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private static final String COUNTRY = "India";
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRANTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MODEL_ID = 1;
	private static final int validProblemsId[] = {1,2,3,4,5,6,7,8,9,10,11,12,15,16,17,19,20,22,24,26,27,28,29};

	private static Faker faker = new Faker(new Locale("en-INd"));
	private static Random random = new Random();

	private FakerDataGenerator() {
	}

	public static CreateJobPayload generateFakeCreateJobData() {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		List<Problem> problemList = generateFakeProbelmList();
		return new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
	}
	
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {
		List<CreateJobPayload>createJobPayloadList = new ArrayList<>();
		for(int i=1;i<=count;i++) {
		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddress();
		CustomerProduct customerProduct = generateFakeCustomerProduct();
		List<Problem> problemList = generateFakeProbelmList();
		CreateJobPayload createJobPayload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);
		createJobPayloadList.add(createJobPayload);
		}
		return createJobPayloadList.iterator();
	}


	private static List<Problem> generateFakeProbelmList() {
		int count = random.nextInt(2)+1;
		List<Problem> problemList = new ArrayList<>();
		String remark;
		int validProblemIdIndex;
		Problem problem;
		for(int i=1;i<=3;i++) {
		
		remark = faker.lorem().sentence(5);
		validProblemIdIndex = random.nextInt(validProblemsId.length);
		problem = new Problem(validProblemsId[validProblemIdIndex], remark);		
		problemList.add(problem);
		}
		return problemList;
	
	}

	private static CustomerProduct generateFakeCustomerProduct() {
		String dop = DateTimeProvider.getDateAndTimeDaysAgo(3);
		String serialNumber = faker.numerify("##############");
		String popUrl = faker.internet().url();
		return new CustomerProduct(serialNumber, serialNumber, serialNumber, dop, popUrl,
				PRODUCT_ID, MODEL_ID);
	}

	private static CustomerAddress generateFakeCustomerAddress() {
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.address().zipCode();

		String state = faker.address().state();

		return new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pincode, state, COUNTRY);
	}

	private static Customer generateFakeCustomerData() {
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber = faker.numerify("999003####");
		String altMobileNumber = faker.numerify("99900#####");
		String customerEmailAddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();
		return new Customer(fname, lname, mobileNumber, altMobileNumber, customerEmailAddress,
				altCustomerEmailAddress);
	}

}
