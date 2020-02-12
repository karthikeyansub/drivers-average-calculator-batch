package com.drivers.data.reader;

import java.io.FileNotFoundException;
import java.util.Map;

import com.drivers.data.model.DriversData;
import com.drivers.exception.TechnicalFailureException;

public interface IDriversDataReader {

	/**
	 * Read driver's data from CSV file
	 * 
	 * @return
	 * 	Map<String, DriversData>
	 * @throws FileNotFoundException
	 * @throws TechnicalFailureException
	 */
	public Map<String, DriversData> readDriversData() throws TechnicalFailureException;
	
}
