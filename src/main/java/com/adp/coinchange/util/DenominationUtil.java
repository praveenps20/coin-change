package com.adp.coinchange.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adp.coinchange.data.CoinChangeData;

/**
 * Utility class for denomination Calculations.
 * 
 * @author Praveen
 *
 */
public class DenominationUtil {

	private static final int DECIMAL_PLACE = 100;

	/**
	 * 
	 * @param target
	 * @return
	 */
	public static Map<Double, Integer> getDenominations(Double target) {
		List<Double> coins = CoinChangeData.getDenominations();
		List<Double> denominations = getDenominations(target * DECIMAL_PLACE, coins, new HashMap<>());
		CoinChangeData.adjust(denominations);
		if (denominations == null) {
			return new HashMap<>();
		}

		Map<Double, Integer> map = new HashMap<>();
		for (Double value : denominations) {
			map.put(value / 100.0, map.getOrDefault(value / 100.0, 0) + 1);
		}
		return map;
	}

	private static List<Double> getDenominations(Double target, List<Double> list, HashMap<Double, List<Double>> map) {

		if (map.containsKey(target)) {
			return map.get(target);
		}
		if (target < 0d) {
			return null;
		}
		if (target == 0.0d) {
			return new ArrayList<>();
		}
		List<Double> shortestCombination = null;
		for (Double num : list) {
			Double reminder = target - num;
			List<Double> reducedList = getReducedList(list, num);
			List<Double> remindeCombination = getDenominations(reminder, reducedList, map);
			if (remindeCombination != null) {
				List<Double> combination = getCombinationList(remindeCombination);
				combination.add(num);
				if (shortestCombination == null || shortestCombination.size() > combination.size()) {
					shortestCombination = combination;
				}
			}
		}
		map.put(target, shortestCombination);
		return shortestCombination;
	}

	private static List<Double> getCombinationList(List<Double> list) {
		List<Double> newList = new ArrayList<>(list.size());
		for (Double d : list) {
			newList.add(d.doubleValue());
		}
		return newList;
	}

	private static List<Double> getReducedList(List<Double> list, Double number) {
		List<Double> newList = new ArrayList<>();
		for (Double d : list) {
			newList.add(d.doubleValue());
		}
		newList.remove(number);
		return newList;
	}
}
