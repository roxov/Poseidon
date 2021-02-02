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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

/**
 * Rest controller for Rating entities.
 *
 */
@RestController
@RequestMapping("rest")
public class RatingRestController {

	private static final Logger LOGGER = LogManager.getLogger(RatingRestController.class);

	@Autowired
	private RatingService ratingService;

	@PostMapping(value = "/rating")
	public Optional<Rating> addRating(@RequestBody Rating rating) {
		LOGGER.info("Adding new rating");
		return Optional.of(ratingService.addRating(rating));
	}

	@GetMapping(value = "/rating")
	public Optional<Rating> findById(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
			return Optional.empty();
		}
		LOGGER.info("Getting rating identified by id");
		return ratingService.findById(id);
	}

	@PutMapping(value = "/rating")
	public Optional<Rating> updateRating(@RequestBody Rating rating) {
		if (rating.getId() == null) {
			LOGGER.error("The id is mandatory.");
			return Optional.empty();
		}
		LOGGER.info("Updating rating");
		return Optional.of(ratingService.updateRating(rating));
	}

	@DeleteMapping(value = "/rating")
	public void deleteRating(@RequestParam Integer id) {
		if (id == null) {
			LOGGER.error("The id must be fielded.");
		} else {
			LOGGER.info("Deleting rating");
			ratingService.deleteRating(id);
		}
	}

}
