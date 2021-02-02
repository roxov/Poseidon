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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

/**
 * Application controller for Trade entities.
 *
 */
@Controller
public class TradeController {

	private static final Logger LOGGER = LogManager.getLogger(TradeController.class);

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private TradeService tradeService;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		LOGGER.info("Getting the trades list");
		model.addAttribute("trades", tradeRepository.findAll());
		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTrade(Trade bid) {
		LOGGER.info("Getting the form to add a trade");
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			LOGGER.info("Adding new trade");
			tradeService.addTrade(trade);
			model.addAttribute("trades", tradeRepository.findAll());
			return "redirect:/trade/list";
		}
		return "trade/add";
	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Trade trade = tradeRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		LOGGER.info("Getting the form to update a trade");
		model.addAttribute("trade", trade);
		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "trade/update";
		}

		trade.setTradeId(id);
		LOGGER.info("Updating trade");
		tradeService.addTrade(trade);
		model.addAttribute("trades", tradeRepository.findAll());
		return "redirect:/trade/list";
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
		LOGGER.info("Deleting trade");
		tradeService.deleteTrade(id);
		model.addAttribute("trades", tradeRepository.findAll());
		return "redirect:/trade/list";
	}
}
