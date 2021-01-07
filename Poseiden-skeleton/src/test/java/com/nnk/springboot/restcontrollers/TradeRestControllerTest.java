package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
public class TradeRestControllerTest {

	@Autowired
	private TradeRestController tradeRestController;

	@MockBean
	private TradeRepository tradeRepository;

	@Test
	public void givenAnAccount_whenFindAllByAccount_thenReturnListWithTheTrade() {
		// GIVEN
		Trade trade = new Trade("account", "type");
		List<Trade> tradeList = new ArrayList<>();
		tradeList.add(trade);
		when(tradeRepository.findAllByAccount("account")).thenReturn(tradeList);

		// WHEN
		List<Trade> result = tradeRestController.findAllByAccount("account");

		// THEN
		verify(tradeRepository, Mockito.times(1)).findAllByAccount("account");
		assertEquals("type", result.get(0).getType());
	}

	@Test
	public void givenATrade_whenAddTrade_thenReturnCreatedTrade() {
		// GIVEN
		Trade trade = new Trade("account", "type");
		when(tradeRepository.save(trade)).thenReturn(trade);

		// WHEN
		Trade result = tradeRestController.addTrade(trade);

		// THEN
		verify(tradeRepository, Mockito.times(1)).save(any(Trade.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
	}
}
