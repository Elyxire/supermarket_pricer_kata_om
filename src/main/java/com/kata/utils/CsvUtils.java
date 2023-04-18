package com.kata.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

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
		InputStream inputStream = CsvUtils.class.getClassLoader().getResourceAsStream(fileName);
		CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(new InputStreamReader(inputStream))
                .withType(className)
                .withIgnoreLeadingWhiteSpace(true)
                .withSeparator(';')
                .build();
        return csvToBean.parse();
    }
}
