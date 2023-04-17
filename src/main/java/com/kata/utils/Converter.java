package com.kata.utils;

import com.kata.enums.UnitEnum;

public class Converter {
	
	private Converter() {
		throw new IllegalStateException("Utility class");
	}
	
	public static double convertToStandardUnit(double quantity, UnitEnum boughtUnit, UnitEnum standardUnit) {
        if (boughtUnit == standardUnit) {
            return quantity;
        }
        double boughtToGramRatio = getRatio(boughtUnit);
        double standardToGramRatio = getRatio(standardUnit);
        return quantity * (boughtToGramRatio / standardToGramRatio);
    }

    private static double getRatio(UnitEnum unit) {
        switch (unit) {
            case MG:
                return 0.001;
            case G:
                return 1.0;
            case KG:
                return 1000.0;
            case OUNCE:
                return 28.3495;
            case POUND:
                return 453.592;
            default:
                throw new IllegalArgumentException("Invalid unit : " + unit);
        }
    }
}
