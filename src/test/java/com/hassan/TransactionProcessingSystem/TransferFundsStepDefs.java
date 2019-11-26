package com.hassan.TransactionProcessingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.service.TransactionService;
import com.hassan.TransactionProcessingSystem.util.Constants;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TransferFundsStepDefs {
	
	@Autowired
	TransactionService transactionService;
	
	private TransRequest transRequest;
	private TransactionResponse transactionResponse;
	private static int ACCOUNT_FIRST_EUR_ID = 1;
	private static int ACCOUNT_SECOND_EUR_ID = 2;

	
	@Given("^2 Accounts with EUR Currency$")
	public void getTwoEURAccounts(){
		transRequest = new TransRequest();
		transRequest.setAccountFrom(ACCOUNT_FIRST_EUR_ID);
		transRequest.setAccountTo(ACCOUNT_SECOND_EUR_ID);
		transRequest.setTransactionAmount(50.0);
	}
	
	@When("^TransRequest is initiated$")
	public void transferRequestInitiated() {
		transactionResponse = transactionService.processtransaction(transRequest);
	}
	
	@Then("^Funds were transfered$")
	public void testFundsTransfer() {
		assertEquals(transactionResponse.getResponseCode(), Constants.RESPONSE_CODE_SUCCESSFULL);
	}

}
