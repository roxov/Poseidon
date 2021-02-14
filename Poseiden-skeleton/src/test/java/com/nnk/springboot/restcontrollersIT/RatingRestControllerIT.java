package com.nnk.springboot.restcontrollersIT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.nnk.springboot.domain.Rating;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingRestControllerIT {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Integer ratingId;

	@BeforeEach
	public void setUp() throws JsonProcessingException, Exception {
		Rating rating = new Rating("moodys", "sandP", "fitch", 1);
		String jsonResponse = mockMvc
				.perform(post("/rest/rating").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(rating)))
				.andReturn().getResponse().getContentAsString();

		ratingId = JsonPath.parse(jsonResponse).read("$.id");
	}

	@Test
	@WithMockUser
	void givenARating_whenPostRating_thenReturns200AndRating() throws Exception {
		Rating rating = new Rating("moodys1", "sandP1", "fitch1", 1);
		mockMvc.perform(post("/rest/rating").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rating))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.moodysRating").value("moodys1"));
	}

	@WithMockUser
	@Test
	public void givenAnId_whenGetRating_thenReturnOkAndRating() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/rest/rating?id={id}", ratingId).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.moodysRating").value("moodys"));
	}

	@Test
	@WithMockUser
	void givenARating_whenPutRating_thenReturns200AndUpdatedRating() throws Exception {
		Rating rating = new Rating(ratingId, "moodys2", "sandP2", "fitch2", 2);
		mockMvc.perform(put("/rest/rating").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rating))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.moodysRating").value("moodys2"));
	}

	@Test
	@WithMockUser
	void givenARating_whenDeleteRating_thenReturns200() throws Exception {
		mockMvc.perform(delete("/rest/rating?id={id}", ratingId)).andExpect(status().isOk());
	}
}
