package com.tanaka.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ExchangeRateAPI exchangeRateAPI = new ExchangeRateAPI();
	
	private Rates conversionRate;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@Autowired
	private Environment enviroment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExcachangeValue(@PathVariable String from, @PathVariable String to) throws Exception {
		conversionRate = exchangeRateAPI.getConversionRate(from, to);

//	Rates rates = conversionRate.getRates();
		Double conversionFactor = getConversionFactor(to, conversionRate);
		ExchangeValue exchangeValue = new ExchangeValue(Long.valueOf((long) Math.random()), from, to, BigDecimal.valueOf(conversionFactor));//repository.findByFromAndTo(from, to);
//		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
		logger.info("{}",exchangeValue);
		
		return exchangeValue;
	}

	private Double getConversionFactor(String to, Rates conversionRate) {
		Double conversionFactor;
		switch (to) {
		case "JPY":
			conversionFactor = conversionRate.getJPY();
			break;
		case "ZAR":
			conversionFactor = conversionRate.getZAR();
			System.out.println("To" + to + " " + conversionFactor + " " + conversionRate.getZAR());
			break;

		case "CAD":
			conversionFactor = conversionRate.getCAD();
			break;
		case "GBP":
			conversionFactor = conversionRate.getGBP();
			break;
		case "IDR":
			conversionFactor = conversionRate.getIDR();
			break;
		case "CNY":
			conversionFactor = conversionRate.getCNY();
			break;
		case "EUR":
			conversionFactor = conversionRate.getEUR();
			break;
		case "MXN":
			conversionFactor = conversionRate.getMXN();
			break;
		case "AUD":
			conversionFactor = conversionRate.getAUD();
			break;
		case "RUB":
			conversionFactor = conversionRate.getRUB();
			break;

		default:
			conversionFactor = 0.0;
			break;

		}
		return conversionFactor;
	}

}
