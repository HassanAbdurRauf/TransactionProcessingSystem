package com.hassan.TransactionProcessingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.util.Constants;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TransferFundsStepDefs {
	
	private RestTemplate restTemplate;
	
	private TransRequest transRequest;
	private TransactionResponse transactionResponse;
	private static int ACCOUNT_FIRST_EUR_ID = 2;
	private static int ACCOUNT_SECOND_EUR_ID = 1;
	private static final String URL = "http://localhost";
	private static String port = "8080";
	private static final String ENDPOINT_PROCESS_TRANSACTION = "/processTransaction";
	
	@Given("Accounts with EUR Currency")
	public void getTwoEURAccounts(){
		transRequest = new TransRequest();
		transRequest.setAccountFrom(ACCOUNT_FIRST_EUR_ID);
		transRequest.setAccountTo(ACCOUNT_SECOND_EUR_ID);
		transRequest.setTransactionAmount(50.0);
	}
	
	@When("TransRequest is initiated")
	public void transferRequestInitiated() {
		this.restTemplate = new RestTemplate();
		ResponseEntity<TransactionResponse>  response = restTemplate.postForEntity(URL+":"+port+ENDPOINT_PROCESS_TRANSACTION, transRequest, TransactionResponse.class);
		assertEquals(200, response.getStatusCodeValue());
		transactionResponse = response.getBody();
		//transactionResponse = transactionService.processtransaction(transRequest);
	}
	
	@Then("Funds were transfered")
	public void testFundsTransfer() {
		assertEquals(transactionResponse.getResponseCode(), Constants.RESPONSE_CODE_SUCCESSFULL);
	}

}
