package com.kata.services;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
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
import com.kata.utils.CsvUtils;

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
	public void testGetTotalPriceAfterPromotions_caseThreeForOneDollar() {
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
        assertEquals(expectedTotal.stripTrailingZeros(), actualTotal.stripTrailingZeros());
	}
	
	@Test
	public void testGetTotalPriceAfterPromotions_caseTwoPoundsForOneDollar() {
		Basket basket = new Basket();
        basket.addItem(new Item("Bean can", new BigDecimal("3.1")));
        basket.addItem(new UncountableItem("Pasta", new BigDecimal("0.56"), UnitEnum.POUND, UnitEnum.POUND, 2.5));
        basket.addItem(new UncountableItem("Rice", new BigDecimal("0.65"), UnitEnum.POUND, UnitEnum.OUNCE, 32));
        List<String> names = Arrays.asList("Pasta", "Rice");
        Set<String> eligibleItemNames = new HashSet<>(names);

        List<Promotion> promotions = Arrays.asList(new TwoPoundsForOneDollarPromotion(eligibleItemNames));
        SupermarketPricer pricer = new SupermarketPricer(promotions);
        BigDecimal expectedTotal = new BigDecimal("5.38");
        BigDecimal actualTotal = pricer.getTotalPriceAfterPromotions(basket);
        assertEquals(expectedTotal.stripTrailingZeros(), actualTotal.stripTrailingZeros());
	}
	
	@Test
	public void testGetTotalPriceAfterPromotions_caseBuyTwoGetOneFreePromotion() {
		Basket basket = new Basket();
        basket.addItem(new Item("Bean can", new BigDecimal("3.1")));
        basket.addItem(new CountableItem("Water bottle", new BigDecimal("2.0"), 4L));
        basket.addItem(new CountableItem("Chips", new BigDecimal("0.5"), 3L));
        List<String> names = Arrays.asList("Water bottle");
        Set<String> eligibleItemNames = new HashSet<>(names);

        List<Promotion> promotions = Arrays.asList(new BuyTwoGetOneFreePromotion(eligibleItemNames));
        SupermarketPricer pricer = new SupermarketPricer(promotions);
        BigDecimal expectedTotal = new BigDecimal("10.6");
        BigDecimal actualTotal = pricer.getTotalPriceAfterPromotions(basket);
        assertEquals(expectedTotal.stripTrailingZeros(), actualTotal.stripTrailingZeros());
	}
	
	@Test
	public void testGetDefaultTotalPriceUsingConverter_caseReadingPricesFromCsvFile() throws IOException {
		List<Item> items = CsvUtils.readDataFromCsvFile("prices.csv", Item.class);
		Basket basket = new Basket();
		basket.setItems(items);
		SupermarketPricer pricer = new SupermarketPricer();
        BigDecimal totalPrice = pricer.getDefaultTotalPrice(basket);

        assertEquals(new BigDecimal("5.55"), totalPrice);
	}
}
