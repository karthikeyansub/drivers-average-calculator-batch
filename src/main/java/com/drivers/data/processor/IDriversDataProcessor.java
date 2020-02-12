package com.drivers.data.processor;

import java.util.List;
import java.util.Map;

import com.drivers.data.model.DriversData;
import com.drivers.exception.TechnicalFailureException;

public interface IDriversDataProcessor {
	
	/**
	 * Calculate the average lap time for each driver.
	 * It returns the drivers list with average
	 * 
	 * @param driversData
	 * 		Map<String, DriversData>
	 * @return
	 * 		List<DriversData>
	 * @throws TechnicalFailureException
	 */
	public List<DriversData> calculateAverage(Map<String, DriversData> driversData) throws TechnicalFailureException;

}
