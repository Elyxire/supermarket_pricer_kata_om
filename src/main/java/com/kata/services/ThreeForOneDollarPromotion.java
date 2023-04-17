package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.models.Basket;

public class ThreeForOneDollarPromotion implements Promotion {
	private static final BigDecimal PROMOTION_PRICE = new BigDecimal("1.00");
    private static final int ITEMS_PER_PROMOTION = 3;
    
    private final Set<String> eligibleItemNames;
    
    public ThreeForOneDollarPromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
        return BigDecimal.ZERO;
	}
    
}
