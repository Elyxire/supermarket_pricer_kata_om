package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.models.Basket;
import com.kata.models.CountableItem;
import com.kata.models.Item;

/**
 * This class is an implementation of the interface {@link Promotion} 
 * This promotion specifies the calculation of the discount amount 
 * for Three For One Dollar promotion
 * 
 * @author Othman
 *
 */

public class ThreeForOneDollarPromotion implements Promotion {
	private static final BigDecimal PROMOTION_PRICE = new BigDecimal("1.00");
    private static final int ITEMS_PER_PROMOTION = 3;
    
    private final Set<String> eligibleItemNames;
    
    public ThreeForOneDollarPromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
		BigDecimal totalDiscount = BigDecimal.ZERO;
        CountableItem countableItem;
        long numEligibleItems;
        long remainingItems;
        for (Item item : basket.getItems()) {
            if(item instanceof CountableItem && eligibleItemNames.contains(item.getName())) {
                countableItem = (CountableItem) item;
                numEligibleItems = countableItem.getQuantity() / ITEMS_PER_PROMOTION;
                remainingItems = countableItem.getQuantity() % ITEMS_PER_PROMOTION;
                BigDecimal subtotalDefault = countableItem.getPrice().multiply(BigDecimal.valueOf(countableItem.getQuantity()));
                BigDecimal subtotalWithDiscount = PROMOTION_PRICE.multiply(BigDecimal.valueOf(numEligibleItems))
                                                   .add(countableItem.getPrice().multiply(BigDecimal.valueOf(remainingItems)));
                BigDecimal discount = subtotalDefault.subtract(subtotalWithDiscount);
                totalDiscount = totalDiscount.add(discount);
            }
        }
        return totalDiscount;
	}
    
}
