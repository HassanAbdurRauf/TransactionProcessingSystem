package com.hassan.TransactionProcessingSystem.bean;

import java.util.Date;
import java.util.Map;

public class ExchangeResponse {
	
	private Map<String, Double> rates;
	private Date date;
	private String base;
	
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	
	

}
