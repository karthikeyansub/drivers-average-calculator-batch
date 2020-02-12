package com.drivers.data.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivers.data.model.DriversData;
import com.drivers.data.processor.IDriversDataProcessor;
import com.drivers.data.reader.IDriversDataReader;
import com.drivers.data.service.IDriversDataService;
import com.drivers.data.writer.IDriversDataWriter;
import com.drivers.exception.TechnicalFailureException;

@Service
public class DriversDataService implements IDriversDataService {
	
	@Autowired
	private IDriversDataReader reader;
	
	@Autowired
	private IDriversDataProcessor processor;
	
	@Autowired
	private IDriversDataWriter writer;

	/**
	 * Read drivers data and calculate the average and generate the report
	 */
	public void generateDriversAverageReport() throws TechnicalFailureException {
		
		Map<String, DriversData> driversData = reader.readDriversData();
		
		List<DriversData> driversList = processor.calculateAverage(driversData);
		
		writer.writeDriversData(driversList);
	}
}
