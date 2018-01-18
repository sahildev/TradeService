package com.spring.fullStack.marketDataService;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MetalService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MetalRepository repository;
	
	public List<Metal> list() {

		log.trace("Entering list()");
		return repository.readAll();
	}
	
	
	public Optional<Metal> create(Metal metal) {

		log.trace("Entering create() with {}", metal);
			
		repository.save(metal);
		return Optional.of(metal);
	}
}
