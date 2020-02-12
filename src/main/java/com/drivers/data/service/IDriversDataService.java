package com.drivers.data.service;

import java.io.IOException;

import com.drivers.exception.TechnicalFailureException;

public interface IDriversDataService {

	public void generateDriversAverageReport() throws TechnicalFailureException;
}
