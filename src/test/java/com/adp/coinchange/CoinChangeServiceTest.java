package com.adp.coinchange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.adp.coinchange.data.CoinChangeData;
import com.adp.coinchange.exception.CoinChangeException;
import com.adp.coinchange.service.CoinChangeService;

class CoinChangeServiceTest {

	CoinChangeService changeService;

	@BeforeEach
	public void setup() {
		changeService = new CoinChangeService();
		CoinChangeData.reset();
	}

	@Test
	void getChangeTestPositive() {
		Map<Double, Integer> map;
		try {
			map = changeService.getChange("1");
			Map<Double, Integer> expectedMap = new HashMap<>();
			expectedMap.put(Double.valueOf(0.25), 4);
			assertEquals(expectedMap, map);
		} catch (CoinChangeException e) {
			e.printStackTrace();
		}
	}

	@Test
	void getChangeTestNegative() {
		Exception exception = assertThrows(CoinChangeException.class, () -> changeService.getChange("50"));

		String expectedMessage = "Not Enough Denominations available";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
	}
	
	@Test
	void setConfigNegativeTest() {
		Exception exception = assertThrows(CoinChangeException.class, () -> changeService.setConfig(null));

		String expectedMessage = "Invalid Configuration input";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.equalsIgnoreCase(expectedMessage));
	}
	

}
