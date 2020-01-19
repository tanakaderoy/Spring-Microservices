package com.tanaka.microservices.currencyexchangeservice;

import org.springframework.web.client.RestTemplate;

public class ExchangeRateAPI {

	private static final String EXCHANGE_API_URL_STRING = "https://api.exchangeratesapi.io/{latest}?base=USD&symbols={symbol1},{symbol2}";
	private RestTemplate restTemplate = new RestTemplate();

	public Rates getConversionRate(String from, String to) throws Exception {
		int test = 0;
		if (isValidSymbol(from) == false) {
			throw new Exception("Not valid Symbol" + from + " " + to);
		}
		ConversionRateModel conversionRateModel = restTemplate.getForObject(EXCHANGE_API_URL_STRING,
				ConversionRateModel.class, "latest", from, to);

		return conversionRateModel.getRates();

	}

	public Boolean isValidSymbol(String symbol) {
		switch (symbol.toUpperCase()) {
		case "JPY":
			return true;
		case "ZAR":
			return true;

		case "CAD":
			return true;

		case "GBP":
			return true;

		case "IDR":
			return true;

		case "CNY":
			return true;

		case "EUR":
			return true;

		case "MXN":
			return true;

		case "AUD":
			return true;

		case "RUB":
			return true;
		case "USD":
			return true;

		default:
			return false;

		}
	}
}
