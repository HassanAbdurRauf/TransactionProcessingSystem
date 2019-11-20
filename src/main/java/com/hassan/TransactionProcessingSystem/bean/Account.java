package com.hassan.TransactionProcessingSystem.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="accounts", catalog = "mydb")
public class Account {
	
	@Id
	@Column(name = "account_id")
	private int accountID;
	@Column(name = "acnt_hldr_fname")
	private String accountHolderFirstName;
	@Column(name = "acnt_hldr_lname")
	private String accountHolderLastName;
	@Column(name = "acnt_hldr_dob")
	private Date accountHolderDateofBirth;
	@Column(name = "is_disabled")
	private char isDisabled;
	@Column(name = "is_locked")
	private char isLocked;
	@Column(name = "balance")
	private float balance;
	@Column(name = "currency_id")
	private int currencyID;
	
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountHolderFirstName() {
		return accountHolderFirstName;
	}
	public void setAccountHolderFirstName(String accountHolderFirstName) {
		this.accountHolderFirstName = accountHolderFirstName;
	}
	public String getAccountHolderLastName() {
		return accountHolderLastName;
	}
	public void setAccountHolderLastName(String accountHolderLastName) {
		this.accountHolderLastName = accountHolderLastName;
	}
	public Date getAccountHolderDateofBirth() {
		return accountHolderDateofBirth;
	}
	public void setAccountHolderDateofBirth(Date accountHolderDateofBirth) {
		this.accountHolderDateofBirth = accountHolderDateofBirth;
	}
	public char getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(char isDisabled) {
		this.isDisabled = isDisabled;
	}
	public char getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(char isLocked) {
		this.isLocked = isLocked;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public int getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(int currencyID) {
		this.currencyID = currencyID;
	}
	
	

}
