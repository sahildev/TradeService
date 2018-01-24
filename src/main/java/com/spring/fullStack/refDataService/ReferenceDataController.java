package com.spring.fullStack.refDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class ReferenceDataController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ReferenceDataService service;

	@RequestMapping(path = "/refdata/{refCode}", method = RequestMethod.GET)
	public RefrenceData read(@PathVariable String refCode) {

		log.info("Entering read() with {}", refCode);
		return service.read(refCode);
	}

}
