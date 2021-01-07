package com.nnk.springboot.restcontrollers;

import java.util.List;

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
import com.nnk.springboot.repositories.TradeRepository;

@RestController
public class TradeRestController {
	private static final Logger LOGGER = LogManager.getLogger(RatingRestController.class);

	@Autowired
	private TradeRepository tradeRepository;

	@GetMapping(value = "/trade")
	public List<Trade> findAllByAccount(@RequestParam String account) {
		LOGGER.info("Getting trade identified by account");
		return tradeRepository.findAllByAccount(account);
	}

	@PostMapping(value = "/trade")
	public Trade addTrade(@RequestBody Trade trade) {
		LOGGER.info("Adding new trade");
		return tradeRepository.save(trade);
	}

	@PutMapping(value = "/trade")
	public Trade updateTrade(@RequestBody Trade trade) {
		LOGGER.info("Updating trade");
		return tradeRepository.save(trade);
	}

	@DeleteMapping(value = "/trade")
	public void deleteTrade(@RequestParam String account) {
		LOGGER.info("Deleting trade");
		tradeRepository.deleteByAccount(account);
	}
}
