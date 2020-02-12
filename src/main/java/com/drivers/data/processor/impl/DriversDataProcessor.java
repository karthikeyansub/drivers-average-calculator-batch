package com.drivers.data.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.drivers.data.model.DriversData;
import com.drivers.data.processor.IDriversDataProcessor;
import com.drivers.exception.TechnicalFailureException;
import com.drivers.util.DriversAverageReportUtil;

@Service
public class DriversDataProcessor implements IDriversDataProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriversDataProcessor.class);
	
	/**
	 * Calculate each driver's average
	 * 
	 * @return
	 */
	@Override
	public List<DriversData> calculateAverage(Map<String, DriversData> driversData)
		throws TechnicalFailureException {
		LOGGER.info("Calcuate drivers average for each driver's");
		
		try {
			final List<DriversData> data = new ArrayList<>();
			
			//Calculate average for each drivers
			driversData.forEach((k, v) -> {
				v.setAverage(DriversAverageReportUtil.format(v.getTotalLapTime()/v.getNumberOfLabs()));
				data.add(v);
			});
			
			LOGGER.debug("Driver's with their average {}" + data.toString());
			
			return data;
		} catch(Exception exception) {
			LOGGER.error("Exception caught while calculating driver's average ", exception);
			throw new TechnicalFailureException(exception.getMessage());
		}
		
	}
}
