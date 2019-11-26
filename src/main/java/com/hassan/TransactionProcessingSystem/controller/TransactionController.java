package com.hassan.TransactionProcessingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	@Autowired
	private AccountsRepository accountsRepository;

	@RequestMapping(value = "/processTransaction", method = RequestMethod. POST, consumes = "application/json", produces = "application/json")
	public TransactionResponse processTransaction(@RequestBody TransRequest transRequest) {
		return transactionService.processtransaction(transRequest);
	}
	
	@RequestMapping(value = "/getBalance", method = RequestMethod. GET, produces = "application/json")
	public Account getBalance(int ID) {
		return accountsRepository.getOne(ID);
	}

}
