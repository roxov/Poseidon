package com.nnk.springboot.restcontrollers;

import java.util.Optional;

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
import com.nnk.springboot.service.BidListService;

@RestController
public class BidListRestController {

	private static final Logger LOGGER = LogManager.getLogger(BidListRestController.class);

	@Autowired
	private BidListService bidListService;

	@GetMapping(value = "/bidList")
	public Optional<BidList> findById(@RequestParam Integer BidListId) {
		LOGGER.info("Getting bid list identified by id");
		return bidListService.findById(BidListId);
	}

	@PostMapping(value = "/bidList")
	public BidList addBidList(@RequestBody BidList bidList) {
		LOGGER.info("Adding new bid list");
		return bidListService.addBidList(bidList);
	}

	@PutMapping(value = "/bidList")
	public BidList updateBidList(@RequestBody BidList bidList) {
		LOGGER.info("Updating bid list");
		return bidListService.updateBidList(bidList);
	}

	@DeleteMapping(value = "/bidList")
	public void deleteBidList(@RequestParam Integer BidListId) {
		LOGGER.info("Deleting bid list");
		bidListService.deleteBidList(BidListId);
	}
}
