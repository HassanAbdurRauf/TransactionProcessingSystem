package com.hassan.TransactionProcessingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.service.TransactionService;
import com.hassan.TransactionProcessingSystem.util.Constants;

@Controller
public class TransactionController {
	
	@GetMapping(value = "/processTransaction")
	public TransactionResponse processTransaction(@RequestParam String param) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setTr_response_code(Constants.RESPONSE_CODE_SUCCESSFULL);
		transactionResponse.setTr_response_code_desc(Constants.RESPONSE_CODE_SUCCESSFULL_DESC);
		
		TransactionService transactionService = new TransactionService();
		//transactionService.
		
		
		return new TransactionResponse();
	}

	

}
