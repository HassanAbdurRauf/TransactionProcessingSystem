package com.hassan.TransactionProcessingSystem.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hassan.TransactionProcessingSystem.bean.ExchangeResponse;

public class CurrencyExchange {

	public static Double getExchangeRate(String fromCurrency, String toCurrency) throws IOException {

		Double exchangeRate = 1.0;

		ObjectMapper objectMapper = new ObjectMapper();

		if (!fromCurrency.equals(toCurrency)) {
			String response = WebClientCommunicator.getRequest(Constants.EXCHANGE_RATE_URL,
					Constants.EXCHANGE_RATE_URL_BASE_PARAM + fromCurrency + Constants.EXCHANGE_RATE_URL_AMP_SIGN
							+ Constants.EXCHANGE_RATE_URL_SYMBOLS_PARAM + toCurrency);

			ExchangeResponse exchangeResponse = objectMapper.readValue(response, ExchangeResponse.class);
			exchangeRate = exchangeResponse.getRates().get(toCurrency);
		}

		return exchangeRate;

	}

}
