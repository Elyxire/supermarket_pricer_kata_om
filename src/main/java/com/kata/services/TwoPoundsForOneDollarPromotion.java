package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.models.Basket;

public class TwoPoundsForOneDollarPromotion implements Promotion {
	private static final BigDecimal PROMOTION_PRICE = new BigDecimal("1.00");
    private static final int UNIT_COUNT_PER_PROMOTION = 2;
    
    private final Set<String> eligibleItemNames;
    
    public TwoPoundsForOneDollarPromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
        return BigDecimal.ZERO;
	}
    
}
