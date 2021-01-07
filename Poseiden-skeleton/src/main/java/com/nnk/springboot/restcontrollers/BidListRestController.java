package com.nnk.springboot.restcontrollers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RestController
public class BidListRestController {

	private static final Logger LOGGER = LogManager.getLogger(BidListRestController.class);

	@Autowired
	private BidListRepository bidListRepository;

	@GetMapping(value = "/bidList")
	public List<BidList> findAllByAccount(@RequestParam String account) {
		LOGGER.info("Getting bid list identified by account");
		return bidListRepository.findAllByAccount(account);
	}

	@PostMapping(value = "/bidList")
	public BidList addBidList(@RequestBody BidList bidList) {
		LOGGER.info("Adding new bid list");
		return bidListRepository.save(bidList);
	}

	@PutMapping(value = "/bidList")
	public BidList updateBidList(@RequestBody BidList bidList) {
		LOGGER.info("Updating bid list");
		return bidListRepository.save(bidList);
	}

	@DeleteMapping(value = "/bidList")
	public void deleteBidList(@RequestParam String account) {
		LOGGER.info("Deleting bid list");
		bidListRepository.deleteByAccount(account);
	}
}
