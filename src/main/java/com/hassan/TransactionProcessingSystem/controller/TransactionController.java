package com.hassan.TransactionProcessingSystem.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.service.TransactionService;
import com.hassan.TransactionProcessingSystem.util.Constants;

@Controller
public class TransactionController {
	
	@RequestMapping(value = "/processTransaction", method = RequestMethod.POST, consumes = "application/json")
	public TransactionResponse processTransaction(@RequestBody TransRequest transRequest) {
		
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setTr_response_code(Constants.RESPONSE_CODE_SUCCESSFULL);
		transactionResponse.setTr_response_code_desc(Constants.RESPONSE_CODE_SUCCESSFULL_DESC);
		
		TransactionService transactionService = new TransactionService();
		try {
			transactionService.processtransaction(transRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			transactionResponse.setTr_response_code(Constants.RESPONSE_CODE_UNSUCCESSFULL);
			transactionResponse.setTr_response_code_desc(Constants.RESPONSE_CODE_UNSUCCESSFULL_DESC);
			e.printStackTrace();
		}
		
		
		return new TransactionResponse();
	}

	

}
