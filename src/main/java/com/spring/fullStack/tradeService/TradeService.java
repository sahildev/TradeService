package com.spring.fullStack.tradeService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.google.gson.Gson;

@Service
public class TradeService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TradeRepository repository;
	
	@Autowired
	private AmazonSNS ssnClient;
	
	 @Value("${aws.sns.topicArn}")
	 private String topicArn;

	public Optional<Trade> read(String tradeId) {

		log.trace("Entering read() with {}", tradeId);
		return repository.read(tradeId);
	}

	public List<Trade> list() {

		log.trace("Entering list()");
		return repository.readAll();
	}

	public Optional<Trade> create(Trade trade) {

		log.trace("Entering create() with {}", trade);
		if (repository.read(trade.getTradeId()).isPresent()) {
			log.warn("trade {} not found", trade.getTradeId());
			return Optional.empty();
		}
		

		Gson gson = new Gson();
		//System.out.println("Marshalled Trade object :"+ gson.toJson(trade));

		String msg = gson.toJson(trade);
		PublishRequest publishRequest = new PublishRequest(topicArn, msg);
		PublishResult publishResult = ssnClient.publish(publishRequest);
		
		repository.save(trade);
		return Optional.of(trade);
	}

	public Optional<Trade> replace(Trade newTradeData) {

		log.trace("Entering replace() with {}", newTradeData);
		Optional<Trade> existingTrade = repository.read(newTradeData.getTradeId());
		if (!existingTrade.isPresent()) {
			log.warn("Trade {} not found", newTradeData.getTradeId());
			return Optional.empty();
		}
		Trade trade = existingTrade.get();
		trade.setQuantity(newTradeData.getQuantity());
		trade.setTradeName(newTradeData.getTradeName());
		repository.save(trade);
		return Optional.of(trade);
	}

	public Optional<Trade> update(Trade newTradeData) {

		log.info("Entering update() with {}", newTradeData);
		Optional<Trade> existingTrade = repository.read(newTradeData.getTradeId());
		if (!existingTrade.isPresent()) {
			log.warn("trade {} not found", newTradeData.getTradeId());
			return Optional.empty();
		}
		Trade trade = existingTrade.get();
		// Add New checks when more fields are exposed
		repository.save(trade);
		return Optional.of(trade);
	}

	public boolean delete(String tradeId) {

		log.trace("Entering delete() with {}", tradeId);
		if (!repository.read(tradeId).isPresent()) {
			log.warn("Trade {} not found", tradeId);
			return false;
		}
		repository.delete(tradeId);
		return true;
	}

}
