package com.hassan.TransactionProcessingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hassan.TransactionProcessingSystem.bean.TransRequest;

@Repository
public interface TransRequestsRepository extends JpaRepository<TransRequest, Integer>{

}
