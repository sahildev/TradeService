package com.spring.fullStack.marketDataService;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1")
@CrossOrigin
public class MetalController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	  @Autowired
	  private MetalService service;
	  
	  @RequestMapping(path = "/metal", method = RequestMethod.GET)
	  public ResponseEntity<List<Metal>> list() {

	    log.trace("Entering list()");
	    List<Metal> metals = service.list();
	    if (metals.isEmpty()) {
	      return new ResponseEntity<>(NO_CONTENT);
	    }
	    return new ResponseEntity<>(metals, OK);
	  }
	  
	  @RequestMapping(path = "/metal", method = RequestMethod.POST)
	  public ResponseEntity<Metal> create(@RequestBody @Valid Metal metal) {

	    log.info("Entering create() with {}", metal);
	    return service.create(metal)
	        .map(newMetalData -> new ResponseEntity<>(newMetalData, CREATED))
	        .orElse(new ResponseEntity<>(CONFLICT));
	  }
	
}
