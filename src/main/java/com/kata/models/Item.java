package com.kata.models;

import java.math.BigDecimal;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.kata.utils.*;

/**
 * This class is the description of an Item from the supermarket
 * 
 * @author Othman
 *
 */

public class Item {
	@CsvBindByName
	private String name;
	@CsvCustomBindByName(column = "price", converter = BigDecimalConverter.class)
    private BigDecimal price;
	
	public Item() {
	}
	
    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

	public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
