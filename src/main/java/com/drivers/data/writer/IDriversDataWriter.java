package com.drivers.data.writer;

import java.util.List;

import com.drivers.data.model.DriversData;
import com.drivers.exception.TechnicalFailureException;

public interface IDriversDataWriter {
	
	public void writeDriversData(List<DriversData> data) throws TechnicalFailureException ;

}
