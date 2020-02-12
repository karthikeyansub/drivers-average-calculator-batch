package com.drivers.data.reader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.collection.IsMapContaining.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.drivers.data.model.DriversData;
import com.drivers.data.reader.impl.DriversDataReader;
import com.drivers.exception.TechnicalFailureException;
import com.drivers.properties.ApplicationProperties;

@RunWith(MockitoJUnitRunner.class)
public class DriversDataReaderTest {
	
	@InjectMocks
	private DriversDataReader reader;
	
	@Mock
	private ApplicationProperties properties;
	
	private Map<String, DriversData> expected;
	
	@Before
	public void setUp() {
		expected = new HashMap<>();
		expected.put("Hamilton", new DriversData("Hamilton", 13.69, 3, null));
        expected.put("Verstrappen", new DriversData("Verstrappen", 13.89, 3, null));
        expected.put("Alonzo", new DriversData("Alonzo", 13.58, 3, null));
	}
	
	@Test(expected=TechnicalFailureException.class)
	public void returnTechnicalFailureExceptionWhenFileNotExists() 
		throws Exception {
		
		when(properties.getInputFilePath()).thenReturn("src/test/resources/test-input/abcd.csv");
		
		reader.readDriversData();
	}
	
	@Test
	public void returnDriversDataWhenFileContainValidData() 
			throws Exception {
		
		when(properties.getInputFilePath()).thenReturn("src/test/resources/test-input/drivers-lap-times-valid-input.csv");
		
		Map<String, DriversData> result = reader.readDriversData();
		
		assertNotNull(result);
		assertThat(result.size(), is(3));
		assertThat(result, hasValue(expected.get("Hamilton")));
		assertThat(result, hasValue(expected.get("Verstrappen")));
		assertThat(result, hasValue(expected.get("Alonzo")));
	}
	
	@Test(expected=TechnicalFailureException.class)
	public void returnTechnicalFailureExceptionWhenFileContainsInvalidData() 
			throws Exception {
		
		when(properties.getInputFilePath()).thenReturn("src/test/resources/test-input/drivers-lap-times-invalid-input.csv");
		
		reader.readDriversData();
	}

}
