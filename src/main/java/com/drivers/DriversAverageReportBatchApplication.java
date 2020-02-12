package com.drivers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.drivers.data.service.IDriversDataService;
import com.drivers.exception.TechnicalFailureException;

@ComponentScan("com.drivers.*")
@SpringBootApplication
public class DriversAverageReportBatchApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriversAverageReportBatchApplication.class);
	
	@Autowired
	private IDriversDataService service;
	
	public static void main(String[] args) {
		SpringApplication.run(DriversAverageReportBatchApplication.class, args).close();
	}
	
	@Override
	public void run(String... args) {
		LOGGER.info("Batch starts...");
		try {
			//Generate driver's average report
			service.generateDriversAverageReport();
		} catch(TechnicalFailureException exception) {
			LOGGER.error("Batch execution failed... ", exception);
		}
	}

}
