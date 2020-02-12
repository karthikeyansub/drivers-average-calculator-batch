package com.drivers.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DriversAverageReportUtil {
	
	/**
	 * Default format for the double value is #.##
	 * 
	 * @param value
	 * @return
	 */
	public static Double format(Double value) {
		return format(value, "#.##");
	}

	/**
	 * Format double value
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static Double format(Double value, String format) {
		DecimalFormat formator = new DecimalFormat(format);
		return Double.valueOf(formator.format(value));
	}
	
	/**
	 * Date formatter
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String format(Date date, String format){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
}
