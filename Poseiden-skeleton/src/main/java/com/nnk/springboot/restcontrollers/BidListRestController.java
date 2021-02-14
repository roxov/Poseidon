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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;

/**
 * Rest controller for BidList entities.
 *
 */

@RestController
@RequestMapping("rest")
public class BidListRestController {

	private static final Logger LOGGER = LogManager.getLogger(BidListRestController.class);

	@Autowired
	private BidListService bidListService;

	@PostMapping(value = "/bidList")
	public Optional<BidList> addBidList(@RequestBody BidList bidList) {
		if (bidList.getAccount() == null || bidList.getType() == null) {
			LOGGER.error("There are missing mandatory fields.");
			return Optional.empty();
		}

		LOGGER.info("Adding new bid list");
		return Optional.of(bidListService.addBidList(bidList));
	}

	@GetMapping(value = "/bidList")
	public Optional<BidList> findById(@RequestParam Integer bidListId) {

		if (bidListId == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}

		LOGGER.info("Getting bid list identified by id");
		return bidListService.findById(bidListId);
	}

	@PutMapping(value = "/bidList")
	public Optional<BidList> updateBidList(@RequestBody BidList bidList) {
		if (bidList.getBidListId() == null) {
			LOGGER.error("There are missing mandatory fields.");
			return Optional.empty();
		}
		LOGGER.info("Updating bid list");
		return Optional.of(bidListService.updateBidList(bidList));
	}

	@DeleteMapping(value = "/bidList")
	public void deleteBidList(@RequestParam Integer bidListId) {
		if (bidListId == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting bid list");
			bidListService.deleteBidList(bidListId);
		}
	}
}
