package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.models.Basket;

public class BuyTwoGetOneFreePromotion implements Promotion {
    private static final int ITEMS_PER_PROMOTION = 3;
    
    private final Set<String> eligibleItemNames;
    
    public BuyTwoGetOneFreePromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
        return BigDecimal.ZERO;
	}
    
}
