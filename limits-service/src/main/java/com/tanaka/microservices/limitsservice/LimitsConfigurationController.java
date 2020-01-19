package com.tanaka.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tanaka.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {
	@Autowired
	private Configuration configutaion;

	@GetMapping("/limits")
	public LimitConfiguration retrieveLimitsFromConfiguration() {
		return new LimitConfiguration(configutaion.getMaximum(), configutaion.getMinimum());
	}
	
	@GetMapping("/fault")
	@HystrixCommand(fallbackMethod = "fallbackRetriveConfiguration")
	public LimitConfiguration retrieveConfiguration() {
		throw new RuntimeException("Gang in this");
	}
	
	public LimitConfiguration fallbackRetriveConfiguration() {
		return new LimitConfiguration(9, 9999);
	}
}
