package com.hassan.TransactionProcessingSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionLog;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;
import com.hassan.TransactionProcessingSystem.service.TransactionService;
import com.hassan.TransactionProcessingSystem.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test"})
class TransactionProcessingSystemApplicationTests {
	
	private static int ACCOUNT_FROM_ID = 1;
	private static int ACCOUNT_TO_ID = 2;
	private static String CURRENCY_EUR = "EUR";
	private static String CURRENCY_USD = "USD";
	
	
	@Mock
	AccountsRepository accountsRepository;
	@Mock
	TransRequestsRepository transRequestsRepository;
	
	Account accountfrom;
	Account accountTo;
	
	TransRequest transRequest;
	
	@InjectMocks
	TransactionService transactionService;
	
	@BeforeTestMethod
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(accountsRepository.getOne(ACCOUNT_FROM_ID)).thenReturn(accountfrom);
		Mockito.when(accountsRepository.getOne(ACCOUNT_TO_ID)).thenReturn(accountTo);
	}
	
	
	
	@BeforeEach
	public void initialize() {
		accountfrom = new Account();
		accountfrom.setAccountID(ACCOUNT_FROM_ID);
		accountfrom.setBalance(100.0);
		accountfrom.setCurrencyCode(CURRENCY_EUR);
		
		accountTo = new Account();
		accountTo.setAccountID(ACCOUNT_TO_ID);
		accountTo.setBalance(50.0);
		accountTo.setCurrencyCode(CURRENCY_USD);
		
		transRequest = new TransRequest();
		transRequest.setAccountFrom(1);
		transRequest.setAccountTo(2);
		transRequest.setTransactionAmount(50.0);
	}
	
	@Test
	void testTransferMoneySameCurrencyWithSufficientFunds() throws IOException {
		Mockito.when(accountsRepository.getOne(ACCOUNT_FROM_ID)).thenReturn(accountfrom);
		Mockito.when(accountsRepository.getOne(ACCOUNT_TO_ID)).thenReturn(accountTo);
		assertEquals(Constants.RESPONSE_CODE_SUCCESSFULL,transactionService.processtransaction(transRequest).getResponseCode());
	}
	
	

}
