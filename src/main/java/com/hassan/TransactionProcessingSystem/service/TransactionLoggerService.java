package com.hassan.TransactionProcessingSystem.bean;

public class TransactionLog {
	
	private TransRequest transRequest;
	private Account accountFrom;
	private Account accountTo;
	
	public TransRequest getTransRequest() {
		return transRequest;
	}
	public void setTransRequest(TransRequest transRequest) {
		this.transRequest = transRequest;
	}
	public Account getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}
	public Account getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}
	

}
