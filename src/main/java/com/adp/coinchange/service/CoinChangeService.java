package com.adp.coinchange.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.adp.coinchange.data.CoinChangeData;
import com.adp.coinchange.exception.CoinChangeException;
import com.adp.coinchange.util.DenominationUtil;
import com.adp.coinchange.util.Validator;

@Service
public class CoinChangeService {

	/**
	 * service method to get the denominations.
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public Map<Double, Integer> getChange(String input) throws CoinChangeException {
		Validator.validate(input);
		Map<Double, Integer> denominations = DenominationUtil.getDenominations(Double.valueOf(input));
		if (denominations.isEmpty()) {
			throw new CoinChangeException("Not Enough Denominations available");
		}
		return denominations;
	}

	/**
	 * reset method
	 */
	public void reset() {
		CoinChangeData.reset();
	}

	/**
	 * 
	 * @param coinMap
	 * @return
	 * @throws Exception 
	 */
	public void setConfig(Map<Double, Integer> coinMap) throws CoinChangeException {
		Validator.validateConfiguration(coinMap);
		CoinChangeData.changeConfigurations(coinMap);
	}
}
