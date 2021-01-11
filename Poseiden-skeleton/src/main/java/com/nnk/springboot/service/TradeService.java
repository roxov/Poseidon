package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {
	private static final Logger LOGGER = LogManager.getLogger(TradeService.class);

	@Autowired
	private TradeRepository tradeRepository;

	public Trade addTrade(Trade trade) {
		LOGGER.info("Adding new trade");
		return tradeRepository.save(trade);
	}

	public Optional<Trade> findById(Integer tradeId) {
		LOGGER.info("Getting trade identified by id");
		return tradeRepository.findById(tradeId);
	}

	public Trade updateTrade(Trade trade) {
		LOGGER.info("Updating trade");
		return tradeRepository.save(trade);
	}

	public void deleteTrade(Integer tradeId) {
		LOGGER.info("Deleting trade");
		tradeRepository.deleteById(tradeId);
	}
}
