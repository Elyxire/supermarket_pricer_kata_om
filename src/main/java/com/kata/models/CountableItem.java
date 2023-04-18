package com.kata.models;

import java.math.BigDecimal;

/**
 * This class represents the items that are countable and inherits from the class {@link Item}
 * 
 * @author Othman
 *
 */

public class CountableItem extends Item{
	private long quantity;
    
    public CountableItem(String name, BigDecimal price) {
        super(name, price);
    }
    
    public CountableItem(String name, BigDecimal price, long quantity) {
        super(name, price);
        this.quantity = quantity;
    }
    
	public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
    	this.quantity = quantity;
    }
}
