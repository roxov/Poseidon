package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeRestControllerTest {
	@Autowired
	private TradeRestController tradeRestController;

	@MockBean
	private TradeService tradeService;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<Trade> result = tradeRestController.findById(null);

		// THEN
		verify(tradeService, Mockito.times(0)).findById(null);
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateTrade_thenReturnEmptyOptional() {
		// GIVEN
		Trade trade = new Trade(null, "account", "type");

		// WHEN
		Optional<Trade> result = tradeRestController.updateTrade(trade);

		// THEN
		verify(tradeService, Mockito.times(0)).updateTrade(trade);
		assertEquals(Optional.empty(), result);
	}
}
