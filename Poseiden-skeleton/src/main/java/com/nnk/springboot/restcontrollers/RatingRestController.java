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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

@RestController
public class RatingRestController {
	private static final Logger LOGGER = LogManager.getLogger(RatingRestController.class);

	@Autowired
	private RatingService ratingService;

	@GetMapping(value = "/rating")
	public Optional<Rating> findById(@RequestParam Integer id) {
		LOGGER.info("Getting rating identified by id");
		return ratingService.findById(id);
	}

	@PostMapping(value = "/rating")
	public Rating addRating(@RequestBody Rating rating) {
		LOGGER.info("Adding new rating");
		return ratingService.addRating(rating);
	}

	@PutMapping(value = "/rating")
	public Rating updateRating(@RequestBody Rating rating) {
		LOGGER.info("Updating rating");
		return ratingService.updateRating(rating);
	}

	@DeleteMapping(value = "/rating")
	public void deleteRating(@RequestParam Integer id) {
		LOGGER.info("Deleting rating");
		ratingService.deleteRating(id);
	}

}
