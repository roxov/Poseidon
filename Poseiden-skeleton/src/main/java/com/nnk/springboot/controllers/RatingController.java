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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

/**
 * Application controller for Rating entities.
 *
 */
@Controller
public class RatingController {

	private static final Logger LOGGER = LogManager.getLogger(RatingController.class);

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		LOGGER.info("Getting the ratings list");
		model.addAttribute("rating", ratingRepository.findAll());
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		LOGGER.info("Getting the form to add a rating");
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		if (!result.hasErrors()) {
			LOGGER.info("Adding new rating");
			ratingService.addRating(rating);
			model.addAttribute("ratings", ratingRepository.findAll());
			return "redirect:/rating/list";
		}
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		LOGGER.info("Getting the form to update a rating");
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "rating/update";
		}

		rating.setId(id);
		LOGGER.info("Updating rating");
		ratingService.addRating(rating);
		model.addAttribute("ratings", ratingRepository.findAll());
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		LOGGER.info("Deleting rating");
		ratingService.deleteRating(id);
		model.addAttribute("ratings", ratingRepository.findAll());
		return "redirect:/rating/list";
	}
}
