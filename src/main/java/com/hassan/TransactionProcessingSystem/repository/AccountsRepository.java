package com.hassan.TransactionProcessingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hassan.TransactionProcessingSystem.bean.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer>{

}
