package com.drivers.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
	
	@Value("${input-file-path}")
	private String inputFilePath;
	
	@Value("${output-file-path}")
	private String outputFilePath;

	/**
	 * Get input file path from application properties
	 * @return
	 */
	public String getInputFilePath() {
		return inputFilePath;
	}

	/**
	 * Get output file path from application properties
	 * @return
	 */
	public String getOutputFilePath() {
		return outputFilePath;
	}

	
}
