package com.spring.fullStack.tradeService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TradeRepository repository;

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

		log.trace("Entering update() with {}", newTradeData);
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
