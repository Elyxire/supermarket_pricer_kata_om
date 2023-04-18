package com.kata.services;

import java.math.BigDecimal;
import java.util.Set;

import com.kata.enums.UnitEnum;
import com.kata.models.Basket;
import com.kata.models.Item;
import com.kata.models.UncountableItem;
import com.kata.utils.Converter;

/**
 * This class is an implementation of the interface {@link Promotion} 
 * This promotion specifies the calculation of the discount amount 
 * for Two Pounds For One Dollar promotion
 * 
 * @author Othman
 *
 */

public class TwoPoundsForOneDollarPromotion implements Promotion {
	private static final BigDecimal PROMOTION_PRICE = new BigDecimal("1.00");
    private static final int UNIT_COUNT_PER_PROMOTION = 2;
    
    private final Set<String> eligibleItemNames;
    
    public TwoPoundsForOneDollarPromotion(Set<String> eligibleItemNames) {
        this.eligibleItemNames = eligibleItemNames;
    }
    
	@Override
	public BigDecimal calculateDiscount(Basket basket) {
		BigDecimal totalDiscount = BigDecimal.ZERO;
		UncountableItem uncountableItem;
        long numEligible;
        double remaining;
        double remainingInStandardUnit;
        double boughtQuantityByPounds;
        for (Item item : basket.getItems()) {
            if(item instanceof UncountableItem && eligibleItemNames.contains(item.getName())) {
            	uncountableItem = (UncountableItem) item;
            	boughtQuantityByPounds = Converter.convertToStandardUnit(uncountableItem.getBoughtQuantity(), uncountableItem.getBoughtUnit(), UnitEnum.POUND);
                numEligible = (long) boughtQuantityByPounds / UNIT_COUNT_PER_PROMOTION;
                remaining = boughtQuantityByPounds % UNIT_COUNT_PER_PROMOTION;
                remainingInStandardUnit = Converter.convertToStandardUnit(remaining, UnitEnum.POUND, uncountableItem.getStandardUnit());
                BigDecimal subtotalDefault = uncountableItem.getPrice().multiply(BigDecimal.valueOf(Converter.convertToStandardUnit(uncountableItem.getBoughtQuantity(), uncountableItem.getBoughtUnit(), uncountableItem.getStandardUnit())));
                BigDecimal subtotalWithDiscount = PROMOTION_PRICE.multiply(BigDecimal.valueOf(numEligible))
                                                   .add(uncountableItem.getPrice().multiply(BigDecimal.valueOf(remainingInStandardUnit)));
                BigDecimal discount = subtotalDefault.subtract(subtotalWithDiscount);
                totalDiscount = totalDiscount.add(discount);
            }
        }
        return totalDiscount;
	}
    
}
