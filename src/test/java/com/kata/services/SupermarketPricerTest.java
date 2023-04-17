package com.kata.services;


import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import com.kata.models.Basket;
import com.kata.models.Item;

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
}
