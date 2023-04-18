package com.kata.utils;

import java.math.BigDecimal;
import com.opencsv.bean.AbstractBeanField;

/**
 * BigDecimalConverter is a utility class used for mapping String value in csv file to BigDecimal
 * 
 * @author Othman
 *
 */

public class BigDecimalConverter extends AbstractBeanField<BigDecimal, String> {
	
	@Override
    protected BigDecimal convert(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return new BigDecimal(value.trim());
    }
}
