package com.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	public static void main(String[] args) {
	List<CreateJobBean>	beanList =  CreateJobPayloadDataDao.getCreateJobPayloadData();
	List<CreateJobPayload>createJobPayloadList = new ArrayList<>();
	System.out.println(beanList);
	for(CreateJobBean bean:beanList) {
	  CreateJobPayload payload = CreateJobBeanMapper.beanMapper(bean);
	  createJobPayloadList.add(payload);
	} System.out.println("-----------------------------------------------");
	  for(CreateJobPayload payload:createJobPayloadList) {
		 System.out.println(payload);
	  }

	}

}
