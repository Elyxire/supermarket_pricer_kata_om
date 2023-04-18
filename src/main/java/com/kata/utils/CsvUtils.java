package com.kata.utils;

import java.util.Collections;
import java.util.List;


/**
 * CsvUtils is a utility class used for reading data from csv file
 * 
 * @author Othman
 *
 */

public class CsvUtils {
	
	private CsvUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * This method reads csv file and return list of the specified object type className
	 * @param <T>
	 * @param fileName
	 * @param className
	 * @return file data as list of type className
	 */
	public static <T> List<T> readDataFromCsvFile(String fileName, Class<T> className) {
        return Collections.emptyList();
    }
}
