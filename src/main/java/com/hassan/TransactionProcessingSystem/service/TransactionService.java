package com.hassan.TransactionProcessingSystem.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionLog;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.util.Constants;
import com.hassan.TransactionProcessingSystem.util.CurrencyExchange;

@Service
public class TransactionService {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private TransactionLoggingService transactionLoggingService;

	public TransactionResponse processtransaction(TransRequest transRequest){

		TransactionResponse transactionResponse = new TransactionResponse();

		try {
			transactionResponse.setResponseCode(Constants.RESPONSE_CODE_SUCCESSFULL);
			transactionResponse.setResponseCodeDesc(Constants.RESPONSE_CODE_SUCCESSFULL_DESC);
			Account accountFrom = accountsRepository.getOne(transRequest.getAccountFrom());
			Account accountTo = accountsRepository.getOne(transRequest.getAccountTo());

			if (accountFrom.getBalance() - transRequest.getTransactionAmount() < 0) {
				transactionResponse.setResponseCode(Constants.RESPONSE_CODE_INSUFFICIENT_FUNDS);
				transactionResponse.setResponseCodeDesc(Constants.RESPONSE_CODE_INSUFFICIENT_FUNDS_DESC);
			} else {
				Double exchaneRate = CurrencyExchange.getExchangeRate(accountFrom.getCurrencyCode(),
						accountTo.getCurrencyCode());
				transRequest.setProcessedAmount(transRequest.getTransactionAmount() * exchaneRate);

				accountFrom.setBalance(accountFrom.getBalance() - transRequest.getTransactionAmount());
				accountTo.setBalance(accountTo.getBalance() + transRequest.getProcessedAmount());
			}

			transRequest.setResponseCode(transactionResponse.getResponseCode());
			transRequest.setTransDtime((new Date(System.currentTimeMillis())));

			TransactionLog transactionLog = new TransactionLog();
			transactionLog.setAccountFrom(accountFrom);
			transactionLog.setAccountTo(accountTo);
			transactionLog.setTransRequest(transRequest);

			transactionLoggingService.logTransaction(transactionLog);

		} catch (IOException e) {
			transactionResponse.setResponseCode(Constants.RESPONSE_CODE_UNSUCCESSFULL);
			transactionResponse.setResponseCodeDesc(Constants.RESPONSE_CODE_UNSUCCESSFULL_DESC);
			e.printStackTrace();
		}

		return transactionResponse;
	}

}
