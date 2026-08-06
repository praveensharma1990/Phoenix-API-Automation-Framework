package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredencials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.database.dao.CreateJobPayloadDataDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
    private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);
	@DataProvider(name = "loginApiDataprovider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		LOGGER.info("Loading data from the CSV file testData/logincreds.csv");
		return CSVReaderUtil.loadCSV("testData/logincreds.csv", UserBean.class);
	}

	@DataProvider(name = "createJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiDataProvider() {
		LOGGER.info("Loading data from the CSV file testData/CreateJobTestData.csv");
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCSV("testData/CreateJobTestData.csv",
				CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.beanMapper(tempBean);
			payloadList.add(tempPayload);

		}
		return payloadList.iterator();
	}

	@DataProvider(name = "createJobAPIFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		LOGGER.info("Generating Fake create job data with the Faker count {}",fakerCountInt);
		return FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);

	}

	@DataProvider(name = "loginApiJsonDataprovider", parallel = true)
	public static Iterator<UserBean> loginAPIJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file testData/loginAPITestData.json");
		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserBean[].class);
	}

	@DataProvider(name = "CreateApiJsonDataprovider", parallel = true)
	public static Iterator<CreateJobPayload> createAPIJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file testData/CreateJobAPIData.json");
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "loginApiExcelDataprovider", parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider() {
		LOGGER.info("Loading data from the Excel file testData/phoenixTestData.xlsx and sheet name is LoginTestData");
		return ExcelReaderUtil.excelReader("testData/phoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}

	@DataProvider(name = "createJobApiExcelDataprovider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiExcelDataprovider() {
		LOGGER.info("Loading data from the Excel file testData/phoenixTestData.xlsx");
		Iterator<CreateJobBean> createJobData = ExcelReaderUtil.excelReader("testData/phoenixTestData.xlsx",
				"CreateJobTestData", CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while (createJobData.hasNext()) {
			tempBean = createJobData.next();
			tempPayload = CreateJobBeanMapper.beanMapper(tempBean);
			payloadList.add(tempPayload);

		}
		return payloadList.iterator();
	} 
	
	@DataProvider(name = "createJobApiDBDataprovider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiDBDataprovider() {
		LOGGER.info("Loadind data from database for createJobPayload");
		List<CreateJobBean>	beanList =  CreateJobPayloadDataDao.getCreateJobPayloadData();
		List<CreateJobPayload>createJobPayloadList = new ArrayList<>();
		System.out.println(beanList);
		for(CreateJobBean bean:beanList) {
		  CreateJobPayload payload = CreateJobBeanMapper.beanMapper(bean);
		  createJobPayloadList.add(payload);
		}  return createJobPayloadList.iterator();

		}


}
