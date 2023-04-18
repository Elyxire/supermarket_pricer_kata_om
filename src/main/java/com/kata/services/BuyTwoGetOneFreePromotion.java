package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.models.Basket;
import com.kata.models.CountableItem;
import com.kata.models.Item;

/**
 * This class is an implementation of the interface {@link Promotion} 
 * This promotion specifies the calculation of the discount amount 
 * for Buy Two Get One Free promotion
 * 
 * @author Othman
 *
 */

public class BuyTwoGetOneFreePromotion implements Promotion {
    private static final int ITEMS_PER_PROMOTION = 3;
    
    private final Set<String> eligibleItemNames;
    
    public BuyTwoGetOneFreePromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
		BigDecimal totalDiscount = BigDecimal.ZERO;
        CountableItem countableItem;
        long numFreeItems;
        for (Item item : basket.getItems()) {
            if(item instanceof CountableItem && eligibleItemNames.contains(item.getName())) {
                countableItem = (CountableItem) item;
                numFreeItems = countableItem.getQuantity() / ITEMS_PER_PROMOTION;
                BigDecimal discount = countableItem.getPrice().multiply(BigDecimal.valueOf(numFreeItems));
                totalDiscount = totalDiscount.add(discount);
            }
        }
        return totalDiscount;
	}
    
}
