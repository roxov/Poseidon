package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListRestControllerTest {
	@Autowired
	private BidListRestController bidListRestController;

	@Test
	public void givenABidListWithoutAccount_whenAddBidList_thenReturnEmptyOptional() {
		// GIVEN
		BidList bidList = new BidList(null, "type", 2.2);

		// WHEN
		Optional<BidList> result = bidListRestController.addBidList(bidList);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenABidListWithoutType_whenAddBidList_thenReturnEmptyOptional() {
		// GIVEN
		BidList bidList = new BidList("account", null, 2.2);

		// WHEN
		Optional<BidList> result = bidListRestController.addBidList(bidList);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<BidList> result = bidListRestController.findById(null);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateBidList_thenReturnEmptyOptional() {
		// GIVEN
		BidList bidList = new BidList(null, "account", "type", 2.2);

		// WHEN
		Optional<BidList> result = bidListRestController.updateBidList(bidList);

		// THEN
		assertEquals(Optional.empty(), result);
	}

}
