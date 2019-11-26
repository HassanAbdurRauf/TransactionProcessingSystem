package com.hassan.TransactionProcessingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;
import com.hassan.TransactionProcessingSystem.service.TransactionLoggerService;
import com.hassan.TransactionProcessingSystem.service.TransactionService;
import com.hassan.TransactionProcessingSystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionProcessingSystemApplicationTests {
	
	private static int ACCOUNT_FIRST_EUR_ID = 1;
	private static int ACCOUNT_SECOND_EUR_ID = 2;
	private static int ACCOUNT_USD_ID = 3;
	private static String CURRENCY_EUR = "EUR";
	private static String CURRENCY_USD = "USD";
	private double accountsOpeningBalance = 100.0;
	
	@Mock
	AccountsRepository accountsRepository;
	@Mock
	TransRequestsRepository transRequestsRepository;
	@Mock
	TransactionLoggerService transactionLoggerService;
	
	Account accountFirstEUR;
	Account accountSecondEUR;
	Account accountUSD;
	
	TransRequest transRequest;
	
	@InjectMocks
	TransactionService transactionService;
	
	@BeforeTestMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeEach
	public void initialize() throws IOException {
		
		accountFirstEUR = new Account();
		accountFirstEUR.setAccountID(ACCOUNT_FIRST_EUR_ID);
		accountFirstEUR.setBalance(accountsOpeningBalance);
		accountFirstEUR.setCurrencyCode(CURRENCY_EUR);
		
		accountSecondEUR = new Account();
		accountSecondEUR.setAccountID(ACCOUNT_SECOND_EUR_ID);
		accountSecondEUR.setBalance(accountsOpeningBalance);
		accountSecondEUR.setCurrencyCode(CURRENCY_EUR);
		
		accountUSD = new Account();
		accountUSD.setAccountID(ACCOUNT_USD_ID);
		accountUSD.setBalance(accountsOpeningBalance);
		accountUSD.setCurrencyCode(CURRENCY_USD);
		
		Mockito.when(accountsRepository.getOne(ACCOUNT_FIRST_EUR_ID)).thenReturn(accountFirstEUR);
		Mockito.when(accountsRepository.getOne(ACCOUNT_SECOND_EUR_ID)).thenReturn(accountSecondEUR);
		Mockito.when(accountsRepository.getOne(ACCOUNT_USD_ID)).thenReturn(accountUSD);
		Mockito.when(transactionLoggerService.log()).thenReturn(true);
	}
	
	@Test
	void testTransferMoneySameCurrencyWithSufficientFunds() throws IOException {
		transRequest = new TransRequest();
		transRequest.setAccountFrom(ACCOUNT_FIRST_EUR_ID);
		transRequest.setAccountTo(ACCOUNT_SECOND_EUR_ID);
		transRequest.setTransactionAmount(50.0);
		
		assertEquals(Constants.RESPONSE_CODE_SUCCESSFULL,transactionService.processtransaction(transRequest).getResponseCode());
		assertEquals(accountFirstEUR.getBalance(), accountsOpeningBalance - transRequest.getTransactionAmount());
		assertEquals(accountSecondEUR.getBalance(), accountsOpeningBalance + transRequest.getProcessedAmount());
	}
	
	@Test
	void testTransferMoneyDifferentCurrencyWithSufficientFunds() throws IOException {
		transRequest = new TransRequest();
		transRequest.setAccountFrom(ACCOUNT_USD_ID);
		transRequest.setAccountTo(ACCOUNT_SECOND_EUR_ID);
		assertEquals(Constants.RESPONSE_CODE_SUCCESSFULL,transactionService.processtransaction(transRequest).getResponseCode());
		assertEquals(accountUSD.getBalance(), accountsOpeningBalance - transRequest.getTransactionAmount());
		assertEquals(accountFirstEUR.getBalance(), accountsOpeningBalance + transRequest.getProcessedAmount());
	}
	
	@Test
	void testTransferMoneyWithInSufficientFunds() throws IOException {
		transRequest = new TransRequest();
		transRequest.setAccountFrom(ACCOUNT_FIRST_EUR_ID);
		transRequest.setAccountTo(ACCOUNT_SECOND_EUR_ID);
		transRequest.setTransactionAmount(150.0);
		
		assertEquals(Constants.RESPONSE_CODE_INSUFFICIENT_FUNDS,transactionService.processtransaction(transRequest).getResponseCode());
		assertEquals(accountFirstEUR.getBalance(), accountsOpeningBalance);
		assertEquals(accountSecondEUR.getBalance(), accountsOpeningBalance);
		assertEquals(transRequest.getProcessedAmount(), 0.0);
	}
	
	

}
