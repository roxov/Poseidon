package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.BidList;

/**
 * 
 * Manage the CRUD operations concerning bid lists.
 *
 */
public interface IBidListService {
	public BidList addBidList(BidList bidList);

	public Optional<BidList> findById(Integer BidListId);

	public BidList updateBidList(BidList bidList);

	public void deleteBidList(Integer BidListId);
}
