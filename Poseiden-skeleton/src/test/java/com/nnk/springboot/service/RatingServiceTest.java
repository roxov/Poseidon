package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {

	@Autowired
	private RatingService ratingService;

	@MockBean
	private RatingRepository ratingRepository;

	@Test
	public void givenARating_whenAddRating_thenReturnCreatedRating() {
		// GIVEN
		Rating rating = new Rating("moodys", "sandP", "fitch", 1);
		when(ratingRepository.save(rating)).thenReturn(rating);

		// WHEN
		Rating result = ratingService.addRating(rating);

		// THEN
		verify(ratingRepository, Mockito.times(1)).save(any(Rating.class));
		assertEquals("moodys", result.getMoodysRating());
		assertEquals("sandP", result.getSandPRating());
		assertEquals("fitch", result.getFitchRating());
		assertEquals(1, result.getOrderNumber());
	}

	@Test
	public void givenAnRating_whenFindById_thenReturnTheRating() {
		// GIVEN
		Rating rating = new Rating(1, "moodys", "sandP", "fitch", 1);
		when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

		// WHEN
		Optional<Rating> result = ratingService.findById(1);

		// THEN
		verify(ratingRepository, Mockito.times(1)).findById(1);
		assertEquals("moodys", result.get().getMoodysRating());
		assertEquals("sandP", result.get().getSandPRating());
		assertEquals("fitch", result.get().getFitchRating());
		assertEquals(1, result.get().getOrderNumber());
	}

	@Test
	public void givenARating_whenUpdateRating_thenReturnUpdatedRating() {
		// GIVEN
		Rating rating = new Rating("moodys", "sandP", "fitch", 1);
		when(ratingRepository.save(rating)).thenReturn(rating);

		// WHEN
		Rating result = ratingService.updateRating(rating);

		// THEN
		verify(ratingRepository, Mockito.times(1)).save(any(Rating.class));
		assertEquals("moodys", result.getMoodysRating());
		assertEquals("sandP", result.getSandPRating());
		assertEquals("fitch", result.getFitchRating());
		assertEquals(1, result.getOrderNumber());
	}

	@Test
	public void givenARating_whenDeleteRating_thenVerifyMethodCalled() {
		// GIVEN
		doNothing().when(ratingRepository).deleteById(1);

		// WHEN
		ratingService.deleteRating(1);

		// THEN
		verify(ratingRepository, Mockito.times(1)).deleteById(any(Integer.class));
	}
}
