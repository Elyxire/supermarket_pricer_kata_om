package com.kata.services;

import java.math.BigDecimal;

import com.kata.models.Basket;
import com.kata.models.Item;

import static java.math.BigDecimal.ZERO;

public class SupermarketPricer {
	public BigDecimal getDefaultTotalPrice(Basket basket) {
		BigDecimal total = ZERO;
		for (Item item : basket.getItems()) {
        	total = total.add(item.getPrice());
        }
        return total;
    }
}
