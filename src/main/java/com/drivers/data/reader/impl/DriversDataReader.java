package com.drivers.data.reader.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivers.data.model.DriversData;
import com.drivers.data.reader.IDriversDataReader;
import com.drivers.exception.TechnicalFailureException;
import com.drivers.properties.ApplicationProperties;
import com.drivers.util.DriversAverageReportUtil;

@Service
public class DriversDataReader implements IDriversDataReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriversDataReader.class);
	
	@Autowired
	public ApplicationProperties properties;

	/**'
	 * Read drivers data from input CSV file and
	 * find each drivers # of laps and total time taken
	 * 
	 * @return 
	 */
	public Map<String, DriversData> readDriversData() 
		throws TechnicalFailureException {
		LOGGER.info("Get drivers average lap time from input CSV file");
		
        String line = "";

        Map<String, DriversData> driversAverage = new HashMap<>();
        
    	try(BufferedReader br = new BufferedReader(new FileReader(properties.getInputFilePath()))) {
    		LOGGER.debug("Input file location: {}", properties.getInputFilePath());
            while ((line = br.readLine()) != null) {
            	LOGGER.debug("Input records {}", line);
                // use comma as separator
                String[] driversData = line.split(",");
                String driverName = driversData[0];
                Double lapTime = Double.valueOf(driversData[1]);
                
                /*
                 * Find each driver's number of laps and total time taken
                 */
                if(null != driversAverage.get(driverName)) {
                	DriversData data = driversAverage.get(driverName);
                	data.setTotalLapTime(DriversAverageReportUtil.format(Double.sum(data.getTotalLapTime(), lapTime)));
                	data.setNumberOfLabs(data.getNumberOfLabs()+1);
                	driversAverage.put(driverName, data);
                } else {
                	driversAverage.put(driverName, new DriversData(driverName, lapTime, 1, null));
                }

            }

        } catch (IOException | IllegalArgumentException exception) {
        	LOGGER.error("Caught exception while reading an input file ", exception);
            throw new TechnicalFailureException(exception.getMessage());
        }
        
        return driversAverage;
	}
}
