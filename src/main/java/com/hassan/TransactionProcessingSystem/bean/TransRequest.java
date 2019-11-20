package com.hassan.TransactionProcessingSystem.bean;

import java.sql.Time;
import java.sql.Timestamp;
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
	@Column(name = "trans_date")
	private Date transDate;
	@Column(name = "trans_time")
	private Time transTime;
	@Column(name = "trans_dtime")
	private Timestamp transDtime;
	@Column(name = "amount_processed")
	private int amountProcessed;
	

}
