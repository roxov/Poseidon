package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingRestControllerTest {
	@Autowired
	private RatingRestController ratingRestController;

	@MockBean
	private RatingService ratingService;

	@Test
	public void givenNullId_whenFindById_thenReturnEmptyOptional() {
		// WHEN
		Optional<Rating> result = ratingRestController.findById(null);

		// THEN
		verify(ratingService, Mockito.times(0)).findById(null);
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void givenANullId_whenUpdateRating_thenReturnEmptyOptional() {
		// GIVEN
		Rating rating = new Rating(null, "moodys", "sandP", "fitch", 1);

		// WHEN
		Optional<Rating> result = ratingRestController.updateRating(rating);

		// THEN
		verify(ratingService, Mockito.times(0)).deleteRating(null);
		assertEquals(Optional.empty(), result);
	}
}
