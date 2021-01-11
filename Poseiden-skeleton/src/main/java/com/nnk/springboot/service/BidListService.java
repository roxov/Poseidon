package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	private static final Logger LOGGER = LogManager.getLogger(BidListService.class);

	@Autowired
	private BidListRepository bidListRepository;

	public BidList addBidList(BidList bidList) {
		LOGGER.info("Adding new bid list");
		return bidListRepository.save(bidList);
	}

	public Optional<BidList> findById(Integer BidListId) {
		LOGGER.info("Getting bid list identified by id");
		return bidListRepository.findById(BidListId);
	}

	public BidList updateBidList(BidList bidList) {
		LOGGER.info("Updating bid list");
		return bidListRepository.save(bidList);
	}

	public void deleteBidList(Integer BidListId) {
		LOGGER.info("Deleting bid list");
		bidListRepository.deleteById(BidListId);
	}
}
