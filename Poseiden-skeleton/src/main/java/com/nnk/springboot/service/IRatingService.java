package com.nnk.springboot.service;

import java.util.Optional;

import com.nnk.springboot.domain.Rating;

/**
 * 
 * Manage the CRUD operations concerning ratings.
 *
 */
public interface IRatingService {
	public Rating addRating(Rating rating);

	public Optional<Rating> findById(Integer id);

	public Rating updateRating(Rating rating);

	public void deleteRating(Integer id);
}
