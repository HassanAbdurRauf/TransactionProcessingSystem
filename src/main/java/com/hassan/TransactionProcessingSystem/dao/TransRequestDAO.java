package com.hassan.TransactionProcessingSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;

public class TransRequestDAO {
	
	@Autowired
	private TransRequestsRepository transRequestsRepository;

}
