package com.spring.fullStack.marketDataService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;

@Repository
public class MetalRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DynamoDBMapper dbMapper;

	public List<Metal> readAll() {

		log.trace("Entering readAll()");
		PaginatedList<Metal> results = dbMapper.scan(Metal.class, new DynamoDBScanExpression());
		results.loadAllResults();
		return results;
	}
	
	public void save(Metal metal) {

		log.trace("Entering save() with {}", metal);
		dbMapper.save(metal);
	}

	public Optional<Metal> read(String metalId) {
		log.trace("Entering read() with {}", metalId);
		return Optional.ofNullable(dbMapper.load(Metal.class, metalId));
	}
	
	
}
