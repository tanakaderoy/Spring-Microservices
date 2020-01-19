package com.tanaka.microservices.currencyconversionservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CurrencyExchangeServiceAPI {
	private static final String EXCHANGE_API_URL_STRING = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
	private RestTemplate restTemplate = new RestTemplate();
	

	public ConversionMultipleModel getConversionMultipleModel(String from, String to) {

		ConversionMultipleModel conversionMultipleModel = restTemplate.getForObject(EXCHANGE_API_URL_STRING,
				ConversionMultipleModel.class, from, to);

		return conversionMultipleModel;
	}
	
	public CurrencyConversionBean getConversionMultipleModelEntity(String from, String to) {
		
		ResponseEntity<CurrencyConversionBean> responseEntity = restTemplate.getForEntity(EXCHANGE_API_URL_STRING, CurrencyConversionBean.class, from,to);
		
		CurrencyConversionBean response = responseEntity.getBody();
		return response;
		
	}

}
