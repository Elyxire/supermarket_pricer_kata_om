package com.kata.services;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.kata.enums.UnitEnum;
import com.kata.models.Basket;
import com.kata.models.CountableItem;
import com.kata.models.Item;
import com.kata.models.UncountableItem;

public class SupermarketPricerTest {
	@Test
    public void testGetDefaultTotalPrice() {
        Basket basket = new Basket();
        basket.addItem(new Item("Bean can", new BigDecimal("3.55")));
        basket.addItem(new Item("Water bottle",  new BigDecimal("2.0")));
        basket.addItem(new Item("Pasta pack",  new BigDecimal("6.0")));

        SupermarketPricer pricer = new SupermarketPricer();
        BigDecimal totalPrice = pricer.getDefaultTotalPrice(basket);

        assertEquals(new BigDecimal("11.55"), totalPrice);
    }
	
	@Test
    public void testGetDefaultTotalPriceUsingConverter() {
        Basket basket = new Basket();
        basket.addItem(new Item("Bean can", new BigDecimal("3.55")));
        basket.addItem(new CountableItem("Water bottle",  new BigDecimal("2.0"), 5L));
        basket.addItem(new UncountableItem("Pasta",  new BigDecimal("6.0"), UnitEnum.KG, UnitEnum.G, 100));

        SupermarketPricer pricer = new SupermarketPricer();
        BigDecimal totalPrice = pricer.getDefaultTotalPrice(basket);

        assertEquals(new BigDecimal("14.15"), totalPrice);
    }
	
	@Test
	public void testGetTotalPriceAfterPromotions() {
		Basket basket = new Basket();
        basket.addItem(new Item("Bean can", new BigDecimal("3.55")));
        basket.addItem(new CountableItem("Water bottle - small",  new BigDecimal("0.4"), 5L));
        basket.addItem(new CountableItem("Chips",  new BigDecimal("0.37"), 3L));
        List<String> names = Arrays.asList("Water bottle - small", "Chips");
        Set<String> eligibleItemNames = new HashSet<>(names);

        List<Promotion> promotions = Arrays.asList(new ThreeForOneDollarPromotion(eligibleItemNames));
        SupermarketPricer pricer = new SupermarketPricer(promotions);
        BigDecimal expectedTotal = new BigDecimal("6.35");
        BigDecimal actualTotal = pricer.getTotalPriceAfterPromotions(basket);
        assertEquals(expectedTotal, actualTotal);
	}
}
