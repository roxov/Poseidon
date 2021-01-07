package com.nnk.springboot.restcontrollers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
public class RatingRestControllerTest {

	@Autowired
	private RatingRestController ratingRestController;

	@MockBean
	private RatingRepository ratingRepository;

	@Test
	public void givenAnOrderNumber_whenFindAllByOrderNumber_thenReturnListWithTheRating() {
		// GIVEN
		Rating rating = new Rating("moodys", "sandP", "fitch", 1);
		List<Rating> ratingList = new ArrayList<>();
		ratingList.add(rating);
		when(ratingRepository.findAllByOrderNumber(1)).thenReturn(ratingList);

		// WHEN
		List<Rating> result = ratingRestController.findAllByOrderNumber(1);

		// THEN
		verify(ratingRepository, Mockito.times(1)).findAllByOrderNumber(1);
		assertEquals("moodys", result.get(0).getMoodysRating());
		assertEquals("sandP", result.get(0).getSandPRating());
		assertEquals("fitch", result.get(0).getFitchRating());
	}

	@Test
	public void givenARating_whenAddRating_thenReturnCreatedRating() {
		// GIVEN
		Rating rating = new Rating("moodys", "sandP", "fitch", 1);
		when(ratingRepository.save(rating)).thenReturn(rating);

		// WHEN
		Rating result = ratingRestController.addRating(rating);

		// THEN
		verify(ratingRepository, Mockito.times(1)).save(any(Rating.class));
		assertEquals("moodys", result.getMoodysRating());
		assertEquals("sandP", result.getSandPRating());
		assertEquals("fitch", result.getFitchRating());
		assertEquals(1, result.getOrderNumber());
	}

}
