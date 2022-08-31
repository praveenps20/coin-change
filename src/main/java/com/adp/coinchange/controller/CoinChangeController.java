package com.adp.coinchange.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adp.coinchange.exception.CoinChangeException;
import com.adp.coinchange.service.CoinChangeService;

@RestController
@RequestMapping("/api")
public class CoinChangeController {

	@Autowired
	private CoinChangeService service;

	@GetMapping("/change")
	public Map<Double, Integer> getChange(@RequestParam("amount") String amount) throws CoinChangeException {
		return service.getChange(amount);
	}

	@PostMapping("/reset")
	public String reset() throws Exception {
		service.reset();
		return "success";
	}

	@PostMapping(path = "/changeConfig", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String changeConfig( @RequestBody Map<Double, Integer> coinMap) throws CoinChangeException {
		service.setConfig(coinMap);

		return "Success";
	}

}
