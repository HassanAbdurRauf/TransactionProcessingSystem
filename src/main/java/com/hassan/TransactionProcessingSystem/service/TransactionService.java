package com.hassan.TransactionProcessingSystem.service;

import java.io.IOException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;
import com.hassan.TransactionProcessingSystem.util.Constants;
import com.hassan.TransactionProcessingSystem.util.CurrencyExchange;

public class TransactionService {
	
	static Session sessionObj;
    static SessionFactory sessionFactoryObj;
	@Autowired
	private AccountsRepository accountsRepository; 
	@Autowired
	private TransRequestsRepository transRequestsRepository;
	
	public String processtransaction(TransRequest transRequest) throws IOException {
		
		String responseCode = Constants.RESPONSE_CODE_SUCCESSFULL;
		Account accountFrom = accountsRepository.getOne(transRequest.getAccountFrom());
		Account accountTo = accountsRepository.getOne(transRequest.getAccountTo());
		
		if (accountFrom.getBalance()-transRequest.getTransactionAmount() < 0) {
			transRequest.setResponseCode(responseCode);
			responseCode = Constants.RESPONSE_CODE_INSUFFICIENT_FUNDS;
		}
		else {
			Double exchaneRate = CurrencyExchange.getExchangeRate(accountFrom.getCurrencyCode(), accountTo.getCurrencyCode());
			transRequest.setProcessedAmount(transRequest.getTransactionAmount() * exchaneRate);
			
			accountFrom.setBalance(accountFrom.getBalance()-transRequest.getTransactionAmount());
			accountTo.setBalance(accountFrom.getBalance()+transRequest.getProcessedAmount());
		}
		
		Transaction transObj = null;
		Session sessionObj = null;
		
		try {
		    sessionObj = buildSessionFactory().openSession();
		    transObj = sessionObj.beginTransaction(); 
		    transRequest.setTransDtime((new Date(System.currentTimeMillis())));
		    transRequestsRepository.save(transRequest);
		    accountsRepository.save(accountFrom);
		    accountsRepository.save(accountTo);
		    transObj.commit();
		} catch (HibernateException exObj) {
		    if(transObj!=null){
		        transObj.rollback();
		    }
		    exObj.printStackTrace(); 
		} finally {
		    sessionObj.close(); 
		}
		
		
		
		return responseCode;
	}
	
	private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure(Constants.HIBERNATE_CONFIG_FILE_PATH);
 
        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
 
        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

}
