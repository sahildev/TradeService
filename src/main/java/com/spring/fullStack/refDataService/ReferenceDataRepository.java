package com.spring.fullStack.refDataService;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ReferenceDataRepository {

	public RefrenceData read(String refCode) {
		
		
		
		Map<String, RefrenceData> RefrenceDataMap = new HashMap<>();
		
		//Commodity
		RefrenceDataMap.put("AU", new RefrenceData("AU","Gold","Gold"));
		RefrenceDataMap.put("AL", new RefrenceData("AL","Aluminium","Aluminium"));
		RefrenceDataMap.put("ZN", new RefrenceData("ZN","Zinc","Zinc"));
		RefrenceDataMap.put("AG", new RefrenceData("AG","Silver","Silver"));
		
		//Location
		RefrenceDataMap.put("LN", new RefrenceData("LN","London","London"));
		RefrenceDataMap.put("NY", new RefrenceData("NY","New York","New York"));
		RefrenceDataMap.put("SG", new RefrenceData("SG","Singapore","Singapore"));
		RefrenceDataMap.put("DN", new RefrenceData("DN","Denver","Denver"));
		
		//Counterparty
		RefrenceDataMap.put("LN", new RefrenceData("1","London","London"));
		RefrenceDataMap.put("LN", new RefrenceData("2","London","London"));
		RefrenceDataMap.put("LN", new RefrenceData("3","London","London"));
		
		System.out.println(RefrenceDataMap.get(refCode));
		return RefrenceDataMap.get(refCode);
	}

	
}
