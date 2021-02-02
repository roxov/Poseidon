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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

/**
 * Rest controller for Trade entities.
 *
 */
@RestController
@RequestMapping("rest")
public class TradeRestController {

	private static final Logger LOGGER = LogManager.getLogger(TradeRestController.class);

	@Autowired
	private TradeService tradeService;

	@PostMapping(value = "/trade")
	public Optional<Trade> addTrade(@RequestBody Trade trade) {
		if (trade.getAccount() == null || trade.getType() == null) {
			LOGGER.error("There are missing mandatory fields.");
			return Optional.empty();
		}
		LOGGER.info("Adding new trade");
		return Optional.of(tradeService.addTrade(trade));
	}

	@GetMapping(value = "/trade")
	public Optional<Trade> findById(@RequestParam Integer tradeId) {
		if (tradeId == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}
		LOGGER.info("Getting trade identified by id");
		return tradeService.findById(tradeId);
	}

	@PutMapping(value = "/trade")
	public Optional<Trade> updateTrade(@RequestBody Trade trade) {
		if (trade.getTradeId() == null || trade.getAccount() == null || trade.getType() == null) {
			LOGGER.error("There are missing mandatory fields.");
			return Optional.empty();
		}
		LOGGER.info("Updating trade");
		return Optional.of(tradeService.updateTrade(trade));
	}

	@DeleteMapping(value = "/trade")
	public void deleteTrade(@RequestParam Integer tradeId) {
		if (tradeId == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting trade");
			tradeService.deleteTrade(tradeId);
		}
	}
}
