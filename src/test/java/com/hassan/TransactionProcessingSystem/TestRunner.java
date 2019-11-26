package com.hassan.TransactionProcessingSystem;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/resources/features"} , glue = {"/TransactionProcessingSystem/src/test/java/com/hassan/TransactionProcessingSystem/TransferFundsStepDefs.java"})
public class TestRunner {
	
	

}
