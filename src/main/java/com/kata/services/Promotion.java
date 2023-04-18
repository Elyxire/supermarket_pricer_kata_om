package com.kata.services;

import java.math.BigDecimal;

import com.kata.models.Basket;

/**
 * This interface provides a method to calculate a basket's discount amount when applying the promotion
 * 
 * @author Othman
 *
 */

public interface Promotion {
	
	/**
	 * This method calculates the discount amount when applying the promotion
	 * 
	 * @param basket
	 * @return basket's discount amount when applying the promotion
	 */
	BigDecimal calculateDiscount(Basket basket);
}
