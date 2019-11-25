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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.bean.TransactionLog;
import com.hassan.TransactionProcessingSystem.bean.TransactionResponse;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;
import com.hassan.TransactionProcessingSystem.util.Constants;
import com.hassan.TransactionProcessingSystem.util.CurrencyExchange;

@Service
public class TransactionService {

	@Value("${spring.profiles.active:}")
	private String activeProfile;
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	@Autowired
	private TransRequestsRepository transRequestsRepository;
	@Autowired
	private AccountsRepository accountsRepository;

	public TransactionResponse processtransaction(TransRequest transRequest) {

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

			if(activeProfile.equals("default"))
				logTransaction(transactionLog);

		} catch (IOException e) {
			transactionResponse.setResponseCode(Constants.RESPONSE_CODE_UNSUCCESSFULL);
			transactionResponse.setResponseCodeDesc(Constants.RESPONSE_CODE_UNSUCCESSFULL_DESC);
			e.printStackTrace();
		}

		return transactionResponse;
	}

	public boolean logTransaction(TransactionLog transactionLog) throws IOException {

		boolean logged = true;

		Transaction transObj = null;
		Session sessionObj = null;

		try {
			sessionObj = buildSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();
			transRequestsRepository.save(transactionLog.getTransRequest());
			accountsRepository.save(transactionLog.getAccountFrom());
			accountsRepository.save(transactionLog.getAccountTo());
			transObj.commit();
		} catch (HibernateException exObj) {
			if (transObj != null) {
				transObj.rollback();
			}
			exObj.printStackTrace();
			logged = false;
			throw new IOException();
		} finally {
			sessionObj.close();
		}

		return logged;
	}

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure(Constants.HIBERNATE_CONFIG_FILE_PATH);

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

}
