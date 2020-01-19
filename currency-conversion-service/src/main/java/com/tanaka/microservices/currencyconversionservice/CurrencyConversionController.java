package com.tanaka.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	CurrencyExchangeServiceAPI api =  new CurrencyExchangeServiceAPI();
	
	ConversionMultipleModel conversionModel;
	CurrencyConversionBean currencyBean;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		conversionModel = api.getConversionMultipleModel(from, to);
		currencyBean = api.getConversionMultipleModelEntity(from, to);

//		return new CurrencyConversionBean((long) Math.random(), from, to, quantity, conversionMultiple,
//				totalCalculatedAmount, port);
//		return new CurrencyConversionBean(1L, from, to, quantity, BigDecimal.valueOf(conversionModel.getConversionMultiple()),
//				BigDecimal.valueOf(conversionModel.getConversionMultiple()).multiply(quantity) , conversionModel.getPort());
		return new CurrencyConversionBean(currencyBean.getId(), from, to, quantity,
				currencyBean.getConversionMultiple(), quantity.multiply(currencyBean.getConversionMultiple()),
				currencyBean.getPort());

	}
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) throws Exception {
		conversionModel = api.getConversionMultipleModel(from, to);
		currencyBean = getConversionMultipleModelFeign(from, to);

//		return new CurrencyConversionBean((long) Math.random(), from, to, quantity, conversionMultiple,
//				totalCalculatedAmount, port);
//		return new CurrencyConversionBean(1L, from, to, quantity, BigDecimal.valueOf(conversionModel.getConversionMultiple()),
//				BigDecimal.valueOf(conversionModel.getConversionMultiple()).multiply(quantity) , conversionModel.getPort());
//		return new CurrencyConversionBean(currencyBean.getId(), from, to, quantity,
//				currencyBean.getConversionMultiple(), quantity.multiply(currencyBean.getConversionMultiple()),
//				currencyBean.getPort());
		logger.info("{}",currencyBean);
		return new CurrencyConversionBean(currencyBean.getId(), from, to, quantity,
				currencyBean.getConversionMultiple(), quantity.multiply(currencyBean.getConversionMultiple()),
				currencyBean.getPort());

	}
	
public CurrencyConversionBean getConversionMultipleModelFeign(String from, String to) throws Exception {
		
	
		
		CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
		return response;
		
	}
}
