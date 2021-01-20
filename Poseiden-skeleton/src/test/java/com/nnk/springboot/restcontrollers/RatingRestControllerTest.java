package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingRestControllerTest {
	@Autowired
	private RatingRestController ratingRestController;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<Rating> result = ratingRestController.findById(null);

		// THEN
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateRating_thenReturnEmptyOptional() {
		// GIVEN
		Rating rating = new Rating(null, "moodys", "sandP", "fitch", 1);

		// WHEN
		Optional<Rating> result = ratingRestController.updateRating(rating);

		// THEN
		assertEquals(Optional.empty(), result);
	}
}
