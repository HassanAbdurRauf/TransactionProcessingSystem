package com.hassan.TransactionProcessingSystem.service;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.hassan.TransactionProcessingSystem.bean.Account;
import com.hassan.TransactionProcessingSystem.bean.TransRequest;
import com.hassan.TransactionProcessingSystem.repository.AccountsRepository;
import com.hassan.TransactionProcessingSystem.repository.TransRequestsRepository;
import com.hassan.TransactionProcessingSystem.util.Constants;

@Component
public class TransactionLoggerService {
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private TransRequestsRepository transRequestsRepository;
	
	private TransRequest transRequest;
	private Account accountFrom;
	private Account accountTo;
	
	public TransRequest getTransRequest() {
		return transRequest;
	}
	public void setTransRequest(TransRequest transRequest) {
		this.transRequest = transRequest;
	}
	public Account getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(Account accountFrom) {
		this.accountFrom = accountFrom;
	}
	public Account getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(Account accountTo) {
		this.accountTo = accountTo;
	}
	
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public boolean log() throws IOException {

		boolean logged = true;

		Transaction transObj = null;
		Session sessionObj = null;

		try {
			sessionObj = buildSessionFactory().openSession();
			transObj = sessionObj.beginTransaction();
			transRequestsRepository.save(transRequest);
			accountsRepository.save(accountFrom);
			accountsRepository.save(accountTo);
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
