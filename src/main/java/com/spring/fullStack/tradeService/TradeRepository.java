package com.spring.fullStack.tradeService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;

@Repository
public class TradeRepository {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private DynamoDBMapper dbMapper;

	public List<Trade> readAll() {

		log.trace("Entering readAll()");
		PaginatedList<Trade> results = dbMapper.scan(Trade.class, new DynamoDBScanExpression());
		results.loadAllResults();
		return results;
	}

	public Optional<Trade> read(String tradeId) {

		log.trace("Entering read() with {}", tradeId);
		return Optional.ofNullable(dbMapper.load(Trade.class, tradeId));
	}

	public void save(Trade trade) {

		log.trace("Entering save() with {}", trade);
		dbMapper.save(trade);
	}

	public void delete(String tradeId) {

		dbMapper.delete(new Trade().withId(tradeId), new DynamoDBMapperConfig(SaveBehavior.CLOBBER));
	}
}
