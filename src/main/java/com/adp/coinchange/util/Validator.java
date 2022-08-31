package com.adp.coinchange.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.adp.coinchange.data.CoinChangeData;
import com.adp.coinchange.exception.CoinChangeException;

/**
 * Utility class for Validations.
 * 
 * @author Praveen
 *
 */
public class Validator {
	private static List<Integer> values = Arrays.asList(1, 2, 5, 10, 20, 50, 100);

	public static void validate(String input) throws CoinChangeException {
		if (input == null || input.trim().isEmpty()) {
			throw new CoinChangeException("Invalid input");
		}
		Integer value = null;
		try {
			value = Integer.valueOf(input);
		} catch (Exception e) {
			throw new CoinChangeException("Invalid input: " + e.getMessage());
		}
		if (!values.contains(value)) {
			throw new CoinChangeException("Invalid input");
		}
		if (CoinChangeData.getDenominations().isEmpty()) {
			throw new CoinChangeException("Not Enough Denominations available");
		}
	}

	public static void validateConfiguration(Map<Double, Integer> coinMap) throws CoinChangeException {
		if (coinMap == null || coinMap.isEmpty()) {
			throw new CoinChangeException("Invalid Configuration input");
		}
	}
}