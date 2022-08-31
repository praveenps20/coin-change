package com.adp.coinchange.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.adp.coinchange.data.CoinChangeData;
import com.adp.coinchange.util.DenominationUtil;
import com.adp.coinchange.util.Validator;

@Service
public class CoinChangeService {

	public Map<Double, Integer> getChange(String input) throws Exception {
		Validator.validate(input);
		Map<Double, Integer> denominations = DenominationUtil.getDenominations(Double.valueOf(input));
		if (denominations.isEmpty()) {
			throw new Exception("Not Enough Denominations available");
		}
		return denominations;
	}

	public void reset() {
		CoinChangeData.reset();
	}
}
