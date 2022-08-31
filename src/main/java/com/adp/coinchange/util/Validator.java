package com.adp.coinchange.util;

import java.util.Arrays;
import java.util.List;

import com.adp.coinchange.data.CoinChangeData;

/**
 * Utility class for Validations.
 * 
 * @author Praveen
 *
 */
public class Validator {
	private static List<Integer> values = Arrays.asList(1, 2, 5, 10, 20, 50, 100);

	public static void validate(String input) throws Exception {
		if (input == null || input.trim().isEmpty()) {
			throw new Exception("Invalid input");
		}
		Integer value = Integer.valueOf(input);
		if (!values.contains(value)) {
			throw new Exception("Invalid input");
		}
		if (CoinChangeData.getDenominations().isEmpty()) {
			throw new Exception("Not Enough Denominations available");
		}
	}
}