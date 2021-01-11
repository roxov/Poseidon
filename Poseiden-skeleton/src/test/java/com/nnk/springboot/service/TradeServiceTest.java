package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {

	@Autowired
	private TradeService tradeService;

	@MockBean
	private TradeRepository tradeRepository;

	@Test
	public void givenATrade_whenAddTrade_thenReturnCreatedTrade() {
		// GIVEN
		Trade trade = new Trade("account", "type");
		when(tradeRepository.save(trade)).thenReturn(trade);

		// WHEN
		Trade result = tradeService.addTrade(trade);

		// THEN
		verify(tradeRepository, Mockito.times(1)).save(any(Trade.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
	}

	@Test
	public void givenATrade_whenFindById_thenReturnTheTrade() {
		// GIVEN
		Trade trade = new Trade(1, "account", "type");
		when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

		// WHEN
		Optional<Trade> result = tradeService.findById(1);

		// THEN
		verify(tradeRepository, Mockito.times(1)).findById(1);
		assertEquals("account", result.get().getAccount());
		assertEquals("type", result.get().getType());
	}

	@Test
	public void givenATrade_whenUpdateTrade_thenReturnUpdatedTrade() {
		// GIVEN
		Trade trade = new Trade(1, "account", "type");
		when(tradeRepository.save(trade)).thenReturn(trade);

		// WHEN
		Trade result = tradeService.updateTrade(trade);

		// THEN
		verify(tradeRepository, Mockito.times(1)).save(any(Trade.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
	}
}
