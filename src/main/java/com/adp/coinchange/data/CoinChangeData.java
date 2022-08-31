package com.adp.coinchange.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data class
 * 
 * @author Praveen
 *
 */
public class CoinChangeData {

	private static Map<Double, Integer> coinMap;

	public static Map<Double, Integer> getCoins() {
		if (coinMap == null) {
			initialize();
		}
		return coinMap;
	}

	private static void initialize() {
		coinMap = new HashMap<>();
		coinMap.put(Double.valueOf(1), 100);
		coinMap.put(Double.valueOf(25), 100);
		coinMap.put(Double.valueOf(10), 100);
		coinMap.put(Double.valueOf(5), 100);
	}

	public static List<Double> getDenominations() {
		List<Double> list = new ArrayList<>();
		for (Map.Entry<Double, Integer> entry : getCoins().entrySet()) {
			for (int i = 0; i < entry.getValue(); i++) {
				list.add(entry.getKey());
			}
		}
		Collections.sort(list, Comparator.reverseOrder());
		return list;
	}

	public static void adjust(List<Double> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (Double value : list) {
			Integer count = coinMap.getOrDefault(value, 0) - 1;
			if (count <= 0) {
				coinMap.remove(value);
			} else {
				coinMap.put(value, count);
			}
		}
	}

	public static void reset() {
		coinMap = null;
	}

	public static void changeConfigurations(Map<Double, Integer> map) {
		if (coinMap == null) {
			coinMap = new HashMap<>();
		} else {
			coinMap.clear();
		}

		for (Map.Entry<Double, Integer> entry : map.entrySet()) {
			coinMap.put(entry.getKey() * 100, entry.getValue());
		}
	}
}
