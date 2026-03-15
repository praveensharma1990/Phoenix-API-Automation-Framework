package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.UserCredencials;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtil2;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtil;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "loginApiDataprovider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCSV("testData/logincreds.csv", UserBean.class);
	}

	@DataProvider(name = "createJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiDataProvider() {

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
		String fakerCount = System.getProperty("fakerCount","5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		return FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);

	}
	
	@DataProvider(name = "loginApiJsonDataprovider", parallel = true)
	public static Iterator<UserCredencials> loginAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/loginAPITestData.json", UserCredencials[].class);
	}
	
	@DataProvider(name = "CreateApiJsonDataprovider", parallel = true)
	public static Iterator<CreateJobPayload> createAPIJsonDataProvider() {
		return JsonReaderUtil.loadJSON("testData/CreateJobAPIData.json",CreateJobPayload[].class);
	}
	
	@DataProvider(name = "loginApiExcelDataprovider", parallel = true)
	public static Iterator<UserBean> loginAPIExcelDataProvider() {
		return ExcelReaderUtil2.excelReader("testData/phoenixTestData.xlsx",UserBean.class);
	}

}
