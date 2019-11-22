package com.hassan.TransactionProcessingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories("com.hassan.TransactionProcessingSystem.repository")
//@EntityScan("com.hassan.TransactionProcessingSystem.bean")
@SpringBootApplication
@EnableAutoConfiguration
public class TransactionProcessingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionProcessingSystemApplication.class, args);
	}

}
