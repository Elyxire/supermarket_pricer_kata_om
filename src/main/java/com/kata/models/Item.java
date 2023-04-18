package com.kata.models;

import java.math.BigDecimal;

/**
 * This class is the description of an Item from the supermarket
 * 
 * @author Othman
 *
 */

public class Item {
	private String name;
    private BigDecimal price;
    
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
