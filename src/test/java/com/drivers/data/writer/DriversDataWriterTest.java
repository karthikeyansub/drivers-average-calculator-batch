package com.drivers.data.writer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.drivers.data.model.DriversData;
import com.drivers.data.writer.impl.DriversDataWriter;
import com.drivers.properties.ApplicationProperties;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DriversDataWriter.class, PrintWriter.class, FileWriter.class })
public class DriversDataWriterTest {
	
	@InjectMocks
	private IDriversDataWriter writer = new DriversDataWriter();
	
	@Mock
	private ApplicationProperties properties;
	
	@Mock
	private FileWriter mockFileWriter;
	
	@Mock
	private PrintWriter mockPrintWriter;
	
	List<DriversData> driversList;
	
	@Before
	public void setUp() {
		driversList = Arrays.asList(
				new DriversData("driver1", 13.5d, 3, 4.5d),
				new DriversData("driver2", 12.0d, 3, 4.0d),
				new DriversData("driver3", 14.6d, 3, 4.87d),
				new DriversData("driver4", 13.4d, 3, 4.47d),
				new DriversData("driver5", 13.1d, 3, 4.37d));
	}
	
	@Test
	public void writeTopThreeDriversRecordsBasedOnLeastLapAverage()
		throws Exception {
		
		PowerMockito.whenNew(FileWriter.class)
	        .withArguments(Mockito.anyString())
	        .thenReturn(mockFileWriter);
		
		PowerMockito.whenNew(PrintWriter.class)
	        .withArguments(mockFileWriter)
	        .thenReturn(mockPrintWriter);

		
		Mockito.when(properties.getOutputFilePath()).thenReturn("test.csv");
		
		writer.writeDriversData(driversList);
		
		Mockito.verify(mockPrintWriter, Mockito.times(1)).println("RANK #1,driver2,4.0");
		Mockito.verify(mockPrintWriter, Mockito.times(1)).println("RANK #2,driver5,4.37");
		Mockito.verify(mockPrintWriter, Mockito.times(1)).println("RANK #3,driver4,4.47");
		
	}

}
