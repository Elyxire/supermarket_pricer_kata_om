package com.kata.services;

import java.math.BigDecimal;

import com.kata.models.Basket;

public interface Promotion {
	BigDecimal calculateDiscount(Basket basket);
}
