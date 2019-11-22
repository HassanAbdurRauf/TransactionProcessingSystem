package com.hassan.TransactionProcessingSystem.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name ="trans_requests", catalog = "mydb")
public class TransRequest {
	
	@Id
	@JsonProperty("trace_audit_no")
	@Column(name = "trace_audit_no")
	private int traceAuditNo;
	@JsonProperty("account_from")
	@Column(name = "account_from")
	private int accountFrom;
	@JsonProperty("account_to")
	@Column(name = "account_to")
	private int accountTo;
	@JsonProperty("response_code")
	@Column(name = "response_code")
	private String responseCode;
	@JsonProperty("trans_dtime")
	@Column(name = "trans_dtime")
	private Date transDtime;
	@JsonProperty("processed_amt")
	@Column(name = "processed_amt")
	private double processedAmount;
	@JsonProperty("trans_amount")
	@Column(name = "trans_amount")
	private double transactionAmount;
	
	public TransRequest() {}
	
	public int getTraceAuditNo() {
		return traceAuditNo;
	}
	public void setTraceAuditNo(int traceAuditNo) {
		this.traceAuditNo = traceAuditNo;
	}
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public Date getTransDtime() {
		return transDtime;
	}
	public void setTransDtime(Date transDtime) {
		this.transDtime = transDtime;
	}
	public double getProcessedAmount() {
		return processedAmount;
	}
	public void setProcessedAmount(double processedAmount) {
		this.processedAmount = processedAmount;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
}
