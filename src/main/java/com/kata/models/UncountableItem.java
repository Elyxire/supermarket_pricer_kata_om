package com.kata.models;

import java.math.BigDecimal;

import com.kata.enums.UnitEnum;

/**
 * This class represents the items that are uncountable (quantity measured by weight or volum ...) 
 * and inherits from the class {@link Item}
 * 
 * @author Othman
 *
 */

public class UncountableItem extends Item{
	private UnitEnum standardUnit;		//unit used for pricing
	private UnitEnum boughtUnit;		//unit used when buying the item
    private double boughtQuantity;
    
	public UncountableItem(String name, BigDecimal price) {
		super(name, price);
	}
	
	public UncountableItem(String name, BigDecimal price, UnitEnum standardUnit, UnitEnum boughtUnit, double boughtQuantity) {
		super(name, price);
		this.standardUnit = standardUnit;
		this.boughtUnit = boughtUnit;
		this.boughtQuantity = boughtQuantity;
	}

	public UnitEnum getStandardUnit() {
		return standardUnit;
	}
	public void setStandardUnit(UnitEnum standardUnit) {
		this.standardUnit = standardUnit;
	}
	public UnitEnum getBoughtUnit() {
		return boughtUnit;
	}
	public void setBoughtUnit(UnitEnum boughtUnit) {
		this.boughtUnit = boughtUnit;
	}
	public double getBoughtQuantity() {
		return boughtQuantity;
	}
	public void setBoughtQuantity(double boughtQuantity) {
		this.boughtQuantity = boughtQuantity;
	}
    
}
