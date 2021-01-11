package com.nnk.springboot.restcontrollers;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

@RestController
public class TradeRestController {
	private static final Logger LOGGER = LogManager.getLogger(TradeRestController.class);

	@Autowired
	private TradeService tradeService;

	@GetMapping(value = "/trade")
	public Optional<Trade> findById(@RequestParam Integer tradeId) {
		LOGGER.info("Getting trade identified by id");
		return tradeService.findById(tradeId);
	}

	@PostMapping(value = "/trade")
	public Trade addTrade(@RequestBody Trade trade) {
		LOGGER.info("Adding new trade");
		return tradeService.addTrade(trade);
	}

	@PutMapping(value = "/trade")
	public Trade updateTrade(@RequestBody Trade trade) {
		LOGGER.info("Updating trade");
		return tradeService.updateTrade(trade);
	}

	@DeleteMapping(value = "/trade")
	public void deleteTrade(@RequestParam Integer tradeId) {
		LOGGER.info("Deleting trade");
		tradeService.deleteTrade(tradeId);
	}
}
