package com.kata.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kata.models.Basket;
import com.kata.models.CountableItem;
import com.kata.models.Item;
import com.kata.models.UncountableItem;
import com.kata.utils.Converter;

import static java.math.BigDecimal.ZERO;

public class SupermarketPricer {
	private List<Promotion> promotions = new ArrayList<>();
	
	public SupermarketPricer() {
    }
	
	public SupermarketPricer(List<Promotion> promotions) {
        this.promotions = promotions;
    }
	
	public BigDecimal getDefaultTotalPrice(Basket basket) {
		BigDecimal total = ZERO;
		CountableItem countableItem;
		UncountableItem uncountableItem;
		for (Item item : basket.getItems()) {
			if(item instanceof CountableItem) {
				countableItem = (CountableItem) item;
				total = total.add(countableItem.getPrice().multiply(BigDecimal.valueOf(countableItem.getQuantity())));
			}
			else if(item instanceof UncountableItem) {
				uncountableItem = (UncountableItem) item;
				double standardQuantity = Converter.convertToStandardUnit(uncountableItem.getBoughtQuantity(), uncountableItem.getBoughtUnit(), uncountableItem.getStandardUnit());
				total = total.add(item.getPrice().multiply(BigDecimal.valueOf(standardQuantity)));
			}
			else {
				total = total.add(item.getPrice());
			}
        }
        return total;
    }
	
	public BigDecimal getTotalPriceAfterPromotions(Basket basket) {
		BigDecimal total = getDefaultTotalPrice(basket);
		for (Promotion promotion : promotions) {
            BigDecimal discount = promotion.calculateDiscount(basket);
            total = total.subtract(discount);
        }
		return total;
	}
}
