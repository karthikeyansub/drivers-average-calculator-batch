package com.drivers.data.processor;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.IsNot.not;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.drivers.data.model.DriversData;
import com.drivers.data.processor.impl.DriversDataProcessor;
import com.drivers.exception.TechnicalFailureException;

@RunWith(MockitoJUnitRunner.class)
public class DriversDataProcessorTest {
	
	@InjectMocks
	private DriversDataProcessor processor;
	
	private Map<String, DriversData> testData;
	
	@Before
	public void setUp() {
		testData = new HashMap<>();
		testData.put("driver1", new DriversData("driver1", 13.5d, 3, null));
		testData.put("driver2", new DriversData("driver2", 12.0d, 3, null));
		testData.put("driver3", new DriversData("driver3", 14.6d, 3, null));
		testData.put("driver4", new DriversData("driver4", 13.4d, 3, null));
		testData.put("driver5", new DriversData("driver5", 13.1d, 3, null));
	}
	
	@Test
	public void returnTopThreeDriversBasedOnLeastAverageWhenFileContainsValidData()
		throws FileNotFoundException, TechnicalFailureException {
		
		List<DriversData> result = processor.calculateAverage(testData);
		
		MatcherAssert.assertThat(result, not(empty()));
		MatcherAssert.assertThat(result, containsInAnyOrder(
				new DriversData("driver1", 13.5d, 3, 4.5d),
				new DriversData("driver2", 12.0d, 3, 4.0d),
				new DriversData("driver3", 14.6d, 3, 4.87d),
				new DriversData("driver4", 13.4d, 3, 4.47d),
				new DriversData("driver5", 13.1d, 3, 4.37d)));
	}
	
}