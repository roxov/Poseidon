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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListRestControllerTest {

	@Autowired
	private BidListRestController bidListRestController;

	@MockBean
	private BidListRepository bidListRepository;

	@Test
	public void givenAnAccount_whenFindAllByAccount_thenReturnListWithTheBidList() {
		// GIVEN
		BidList bidList = new BidList("account", "type", 2.2);
		List<BidList> ListOfBidList = new ArrayList<>();
		ListOfBidList.add(bidList);
		when(bidListRepository.findAllByAccount("account")).thenReturn(ListOfBidList);

		// WHEN
		List<BidList> result = bidListRestController.findAllByAccount("account");

		// THEN
		verify(bidListRepository, Mockito.times(1)).findAllByAccount("account");
		assertEquals("type", result.get(0).getType());
		assertEquals(2.2, result.get(0).getBidQuantity());
	}

	@Test
	public void givenABidList_whenAddBidList_thenReturnCreatedBidList() {
		// GIVEN
		BidList bidList = new BidList("account", "type", 2.2);
		when(bidListRepository.save(bidList)).thenReturn(bidList);

		// WHEN
		BidList result = bidListRestController.addBidList(bidList);

		// THEN
		verify(bidListRepository, Mockito.times(1)).save(any(BidList.class));
		assertEquals("account", result.getAccount());
		assertEquals("type", result.getType());
		assertEquals(2.2, result.getBidQuantity());
	}

}
