package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeRestControllerTest {
	@Autowired
	private TradeRestController tradeRestController;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<Trade> result = tradeRestController.findById(null);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateTrade_thenReturnEmptyOptional() {
		// GIVEN
		Trade trade = new Trade(null, "account", "type");

		// WHEN
		Optional<Trade> result = tradeRestController.updateTrade(trade);

		// THEN
		assertEquals(Optional.empty(), result);
	}
}
