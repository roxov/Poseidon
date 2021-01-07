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

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@RestController
public class RatingRestController {
	private static final Logger LOGGER = LogManager.getLogger(RatingRestController.class);

	@Autowired
	private RatingRepository ratingRepository;

	@GetMapping(value = "/rating")
	public List<Rating> findAllByOrderNumber(@RequestParam Integer orderNumber) {
		LOGGER.info("Getting rating identified by order number");
		return ratingRepository.findAllByOrderNumber(orderNumber);
	}

	@PostMapping(value = "/rating")
	public Rating addRating(@RequestBody Rating rating) {
		LOGGER.info("Adding new rating");
		return ratingRepository.save(rating);
	}

	@PutMapping(value = "/rating")
	public Rating updateRating(@RequestBody Rating rating) {
		LOGGER.info("Updating rating");
		return ratingRepository.save(rating);
	}

	@DeleteMapping(value = "/rating")
	public void deleteRating(@RequestParam Integer orderNumber) {
		LOGGER.info("Deleting rating");
		ratingRepository.deleteByOrderNumber(orderNumber);
	}

}
