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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTest {

	@Autowired
	private BidListService bidListService;

	@MockBean
	private BidListRepository bidListRepository;

	@Test
	public void givenABidList_whenAddBidList_thenReturnCreatedBidList() {
		// GIVEN
		BidList bidList = new BidList("account", "type", 2.2);
		when(bidListRepository.save(bidList)).thenReturn(bidList);

		// WHEN
		BidList result = bidListService.addBidList(bidList);

		// THEN
		verify(bidListRepository, Mockito.times(1)).save(any(BidList.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
		assertEquals(2.2, result.getBidQuantity());
	}

	@Test
	public void givenABidList_whenFindById_thenReturnTheBidList() {
		// GIVEN
		BidList bidList = new BidList(1, "account", "type", 2.2);
		when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

		// WHEN
		Optional<BidList> result = bidListService.findById(1);

		// THEN
		verify(bidListRepository, Mockito.times(1)).findById(1);
		assertEquals("account", result.get().getAccount());
		assertEquals("type", result.get().getType());
		assertEquals(2.2, result.get().getBidQuantity());
	}

	@Test
	public void givenABidList_whenUpdateBidList_thenReturnUpdatedBidList() {
		// GIVEN
		BidList bidList = new BidList("account", "type", 2.2);
		when(bidListRepository.save(bidList)).thenReturn(bidList);

		// WHEN
		BidList result = bidListService.updateBidList(bidList);

		// THEN
		verify(bidListRepository, Mockito.times(1)).save(any(BidList.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
		assertEquals(2.2, result.getBidQuantity());
	}

}
