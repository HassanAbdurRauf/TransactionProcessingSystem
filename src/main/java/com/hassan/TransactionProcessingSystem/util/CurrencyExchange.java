package com.hassan.TransactionProcessingSystem.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hassan.TransactionProcessingSystem.bean.ExchangeResponse;

public class CurrencyExchange {

	public static Double getExchangeRate(String fromCurrency, String toCurrency) throws IOException{

		ObjectMapper objectMapper = new ObjectMapper();
		String response = WebClientCommunicator.getRequest("base="+fromCurrency+"&sybmbols="+toCurrency);

		ExchangeResponse exchangeResponse = objectMapper.readValue(response, ExchangeResponse.class);

		return exchangeResponse.getRates().get(toCurrency);
		
	}

}
