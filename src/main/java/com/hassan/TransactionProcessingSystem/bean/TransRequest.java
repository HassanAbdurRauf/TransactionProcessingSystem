package com.hassan.TransactionProcessingSystem.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="trans_requests", catalog = "mydb")
public class TransRequest {
	
	@Id
	@Column(name = "trace_audit_no")
	private int traceAuditNo;
	@Column(name = "account_from")
	private int AccountFrom;
	@Column(name = "account_to")
	private int AccountTo;
	@Column(name = "response_code")
	private String responseCode;
	@Column(name = "trans_dtime")
	private Date transDtime;
	@Column(name = "processed_amt")
	private double processedAmount;
	@Column(name = "trans_amount")
	private double transactionAmount;
	
	public int getTraceAuditNo() {
		return traceAuditNo;
	}
	public void setTraceAuditNo(int traceAuditNo) {
		this.traceAuditNo = traceAuditNo;
	}
	public int getAccountFrom() {
		return AccountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		AccountFrom = accountFrom;
	}
	public int getAccountTo() {
		return AccountTo;
	}
	public void setAccountTo(int accountTo) {
		AccountTo = accountTo;
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
