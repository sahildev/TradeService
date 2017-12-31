package com.spring.fullStack.tradeService;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class TradeController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	  @Autowired
	  private TradeService service;

	  @RequestMapping(path = "/trade", method = RequestMethod.GET)
	  public ResponseEntity<List<Trade>> list() {

	    log.trace("Entering list()");
	    List<Trade> trades = service.list();
	    if (trades.isEmpty()) {
	      return new ResponseEntity<>(NO_CONTENT);
	    }
	    return new ResponseEntity<>(trades, OK);
	  }

	  @RequestMapping(path = "/trade/{tradeId}", method = RequestMethod.GET)
	  public ResponseEntity<Trade> read(@PathVariable String tradeId) {

	    log.info("Entering read() with {}", tradeId);
	    return service.read(tradeId)
	        .map(trade -> new ResponseEntity<>(trade, OK))
	        .orElse(new ResponseEntity<>(NOT_FOUND));
	  }

	  @RequestMapping(path = "/trade", method = RequestMethod.POST)
	  public ResponseEntity<Trade> create(@RequestBody @Valid Trade trade) {

	    log.info("Entering create() with {}", trade);
	    return service.create(trade)
	        .map(newTradeData -> new ResponseEntity<>(newTradeData, CREATED))
	        .orElse(new ResponseEntity<>(CONFLICT));
	  }

	  @RequestMapping(path = "/trade/{tradeId}", method = RequestMethod.PUT)
	  public ResponseEntity<Trade> put(@PathVariable String tradeId, @RequestBody Trade trade) {

	    log.info("Entering put() with {}, {}", tradeId, trade);
	    return service.replace(trade.withId(tradeId))
	        .map(newTradeData -> new ResponseEntity<>(newTradeData, OK))
	        .orElse(new ResponseEntity<>(NOT_FOUND));
	  }

	  @RequestMapping(path = "/trade/{tradeId}", method = RequestMethod.PATCH)
	  public ResponseEntity<Trade> patch(@PathVariable String tradeId, @RequestBody Trade trade) {

	    log.trace("Entering patch() with {}, {}", tradeId, trade);
	    return service.update(trade.withId(tradeId))
	        .map(newTradeData -> new ResponseEntity<>(newTradeData, OK))
	        .orElse(new ResponseEntity<>(NOT_FOUND));
	  }

	  @RequestMapping(path = "/trade/{tradeId}", method = RequestMethod.DELETE)
	  public ResponseEntity<Void> delete(@PathVariable String tradeId) {

	    log.trace("Entering delete() with {}", tradeId);
	    return service.delete(tradeId) ?
	        new ResponseEntity<>(NO_CONTENT) :
	        new ResponseEntity<>(NOT_FOUND);
	  }

}
