package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.model.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil3 {
	public static void main(String[] args) {
	Iterator<CreateJobBean>createJobData=ExcelReaderUtil2.excelReader("testData/phoenixTestData.xlsx", "CreateJobTestData",CreateJobBean.class);
	while(createJobData.hasNext()) {
		CreateJobBean createJobBean = createJobData.next();
		CreateJobPayload createJobPayload = CreateJobBeanMapper.beanMapper(createJobBean);
		System.out.println(createJobPayload);
	}
		
	}
}
