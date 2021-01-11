package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.Trade;

/**
 * 
 * Manage the CRUD operations concerning trades.
 *
 */
public interface ITradeService {
	public Trade addTrade(Trade trade);

	public Optional<Trade> findById(Integer tradeId);

	public Trade updateTrade(Trade trade);

	public void deleteTrade(Integer tradeId);
}
