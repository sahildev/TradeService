package com.spring.fullStack.refDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenceDataService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ReferenceDataRepository repository;

	public RefrenceData read(String refCode) {
		log.trace("Entering read() with {}", refCode);
		return repository.read(refCode);
	}

}
