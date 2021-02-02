package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

/**
 * Application controller for BidList entities.
 *
 */
@Controller
public class BidListController {

	private static final Logger LOGGER = LogManager.getLogger(BidListController.class);

	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private BidListService bidListService;

	@RequestMapping("/bidList/list")
	public String home(Model model) {
		LOGGER.info("Getting the list of bid list");
		model.addAttribute("bidList", bidListRepository.findAll());
		return "bidList/list";
	}

	@GetMapping("/bidList/add")
	public String addBidForm(BidList bid) {
		LOGGER.info("Getting the form to add a bid list");
		return "bidList/add";
	}

	@PostMapping("/bidList/validate")
	public String validate(@Valid BidList bid, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			LOGGER.info("Adding new bid list");
			bidListService.addBidList(bid);
			model.addAttribute("bidList", bidListRepository.findAll());
			return "redirect:/bidList/list";
		}
		return "bidList/add";
	}

	@GetMapping("/bidList/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		BidList bidList = bidListRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		LOGGER.info("Getting the form to update a bid list");
		model.addAttribute("bidList", bidList);
		return "bidList/update";
	}

	@PostMapping("/bidList/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "bidList/update";
		}
		LOGGER.info("Updating bid list");
		bidList.setBidListId(id);
		bidListService.addBidList(bidList);
		model.addAttribute("bidList", bidListRepository.findAll());
		return "redirect:/bidList/list";
	}

	@GetMapping("/bidList/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
		LOGGER.info("Deleting bid list");
		bidListService.deleteBidList(id);
		model.addAttribute("bidList", bidListRepository.findAll());
		return "redirect:/bidList/list";
	}
}
