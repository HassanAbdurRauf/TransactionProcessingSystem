package com.hassan.TransactionProcessingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hassan.TransactionProcessingSystem.bean.Account;

public interface AccountsRepository extends JpaRepository<Account, Integer>{

}
