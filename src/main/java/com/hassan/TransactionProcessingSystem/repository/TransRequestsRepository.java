package com.hassan.TransactionProcessingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hassan.TransactionProcessingSystem.bean.TransRequest;

public interface TransRequestsRepository extends JpaRepository<TransRequest, Integer>{

}
