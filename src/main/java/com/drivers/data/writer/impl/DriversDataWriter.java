package com.drivers.data.writer.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivers.data.model.DriversData;
import com.drivers.data.writer.IDriversDataWriter;
import com.drivers.exception.TechnicalFailureException;
import com.drivers.properties.ApplicationProperties;
import com.drivers.util.DriversAverageReportUtil;

@Service
public class DriversDataWriter implements IDriversDataWriter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriversDataWriter.class);
	
	@Autowired
	private ApplicationProperties properties;

	/**
	 * Generate top three rank driver's report
	 */
	@Override
	public void writeDriversData(List<DriversData> data)
		throws TechnicalFailureException {
		
		try { 
			String fileName = MessageFormat.format(properties.getOutputFilePath(),
					DriversAverageReportUtil.format(new Date(), "ddMMyyyy"));
			LOGGER.info("Output file name: {}", fileName);
			
			FileWriter fileWriter = new FileWriter(fileName);
			
		    try (PrintWriter pw = new PrintWriter(fileWriter)) {
		    	
		    	//Write top three rank drivers data
		    	AtomicInteger count= new AtomicInteger(1);
		    	data.stream().sorted(Comparator.comparingDouble(DriversData::getAverage)).limit(3)
		          .forEach(d -> pw.println(String.join(",", ("RANK #"+ count.getAndIncrement()), d.getName(), d.getAverage().toString())));
		    	
		    } 
		    
		    LOGGER.info("Report generated successfully.");
		} catch(IOException exception) {
			LOGGER.error("Exception caught while writing file ", exception);
			throw new TechnicalFailureException(exception.getMessage());
		}
	}

}
