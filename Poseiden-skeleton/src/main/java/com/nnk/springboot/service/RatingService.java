package com.nnk.springboot.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	private static final Logger LOGGER = LogManager.getLogger(RatingService.class);

	@Autowired
	private RatingRepository ratingRepository;

	public Rating addRating(Rating rating) {
		LOGGER.info("Adding new rating");
		return ratingRepository.save(rating);
	}

	public Optional<Rating> findById(Integer id) {
		LOGGER.info("Getting rating identified by id");
		return ratingRepository.findById(id);
	}

	public Rating updateRating(Rating rating) {
		LOGGER.info("Updating rating");
		return ratingRepository.save(rating);
	}

	public void deleteRating(Integer id) {
		LOGGER.info("Deleting rating");
		ratingRepository.deleteById(id);
	}
}
